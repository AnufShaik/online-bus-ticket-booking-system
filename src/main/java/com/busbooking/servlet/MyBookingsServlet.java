
package com.busbooking.servlet;

import com.busbooking.entity.booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/myBookings")
public class MyBookingsServlet extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() {
        factory = Persistence.createEntityManagerFactory("busbookingPU");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        EntityManager em = factory.createEntityManager();

        try {
            List<booking> bookings = em.createQuery(
                            "SELECT b FROM booking b " +
                                    "JOIN FETCH b.bus " +
                                    "WHERE b.user.id = :userId ORDER BY b.bookingDate DESC",
                            booking.class)
                    .setParameter("userId", userId)
                    .getResultList();

            request.setAttribute("bookings", bookings);

            request.getRequestDispatcher("mybookings.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}

