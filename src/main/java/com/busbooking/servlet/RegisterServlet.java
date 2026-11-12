
package com.busbooking.servlet;

import com.busbooking.entity.user;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() {
        factory = Persistence.createEntityManagerFactory("busbookingPU");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            Long count = em.createQuery(
                            "SELECT COUNT(u) FROM user u WHERE u.email = :email",
                            Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (count > 0) {
                response.getWriter().println("Email already registered!");
                return;
            }

            user user = new user();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);

            tx.begin();
            em.persist(user);
            tx.commit();

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
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
