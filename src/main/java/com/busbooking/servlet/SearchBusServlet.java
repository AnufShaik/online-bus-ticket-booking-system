
package com.busbooking.servlet;

import com.busbooking.entity.bus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchBus")
public class SearchBusServlet extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() {
        factory = Persistence.createEntityManagerFactory("busbookingPU");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String source = request.getParameter("source");
        String destination = request.getParameter("destination");

        EntityManager em = factory.createEntityManager();

        try {
            List<bus> buses = em.createQuery(
                            "SELECT b FROM bus b WHERE LOWER(b.source) = LOWER(:source) " +
                                    "AND LOWER(b.destination) = LOWER(:destination)",
                            bus.class)
                    .setParameter("source", source)
                    .setParameter("destination", destination)
                    .getResultList();

            request.setAttribute("buses", buses);

            request.getRequestDispatcher("buses.jsp")
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

