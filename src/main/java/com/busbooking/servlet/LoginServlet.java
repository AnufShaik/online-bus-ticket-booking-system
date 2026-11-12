
package com.busbooking.servlet;

import com.busbooking.entity.user;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private EntityManagerFactory factory;

    @Override
    public void init() {
        factory = Persistence.createEntityManagerFactory("busbookingPU");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        EntityManager em = factory.createEntityManager();

        try {
            List<user> users = em.createQuery(
                            "SELECT u FROM user u WHERE u.email = :email AND u.password = :password",
                            user.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getResultList();

            if (!users.isEmpty()) {
                user user = users.get(0);

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getName());

                response.sendRedirect("index.jsp");

            } else {
                response.getWriter().println("Invalid email or password!");
            }

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

