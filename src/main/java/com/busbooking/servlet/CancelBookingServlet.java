
package com.busbooking.servlet;

import com.busbooking.entity.booking;
import com.busbooking.entity.bus;

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

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {

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
            int bookingId = Integer.parseInt(
                    request.getParameter("bookingId"));

            EntityManager em = factory.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                booking booking = em.find(
                        booking.class,
                        bookingId,
                        LockModeType.PESSIMISTIC_WRITE);

                if (booking == null || booking.getUser().getId() != userId) {
                    tx.rollback();
                    response.getWriter().println("Booking not found!");
                    return;
                }

                if ("CANCELLED".equals(booking.getStatus())) {
                    tx.rollback();
                    response.getWriter().println("Booking already cancelled!");
                    return;
                }

                bus bus = em.find(
                        bus.class,
                        booking.getBus().getId(),
                        LockModeType.PESSIMISTIC_WRITE);

                bus.setAvailableSeats(
                        bus.getAvailableSeats() + booking.getSeatsBooked());

                booking.setStatus("CANCELLED");

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
            response.getWriter().println("Invalid booking ID!");
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
