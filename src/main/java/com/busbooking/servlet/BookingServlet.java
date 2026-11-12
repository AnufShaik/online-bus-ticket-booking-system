
package com.busbooking.servlet;

import com.busbooking.entity.booking;
import com.busbooking.entity.bus;
import com.busbooking.entity.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() {
        factory = Persistence.createEntityManagerFactory("busbookingPU");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int userId = (Integer) session.getAttribute("userId");
            int busId = Integer.parseInt(request.getParameter("busId"));
            int seats = Integer.parseInt(request.getParameter("seatsBooked"));

            if (seats <= 0) {
                response.getWriter().println("Enter a valid number of seats!");
                return;
            }

            EntityManager em = factory.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                bus bus = em.find(
                        bus.class, busId, LockModeType.PESSIMISTIC_WRITE);

                user user = em.find(user.class, userId);

                if (bus == null || user == null) {
                    tx.rollback();
                    response.getWriter().println("Bus or user not found!");
                    return;
                }

                if (bus.getAvailableSeats() < seats) {
                    tx.rollback();
                    response.getWriter().println("Not enough seats available!");
                    return;
                }

                bus.setAvailableSeats(bus.getAvailableSeats() - seats);

                booking booking = new booking();
                booking.setUser(user);
                booking.setBus(bus);
                booking.setSeatsBooked(seats);
                booking.setTotalAmount(bus.getPrice() * seats);
                booking.setBookingDate(LocalDateTime.now());
                booking.setStatus("CONFIRMED");

                em.persist(booking);

                tx.commit();

                response.sendRedirect("myBookings");

            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            } finally {
                em.close();
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid bus ID or seat count!");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}

