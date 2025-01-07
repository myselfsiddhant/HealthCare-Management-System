package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.UserCredentials;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // Initialize DAO
    }

    // GET method to show registration form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    // POST method to handle registration
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserCredentials user = new UserCredentials();
        user.setUsername(username);
        user.setPassword(password);

        try {
            boolean registrationSuccess = registerUser(user);
            
            if (registrationSuccess) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.setAttribute("message", "Registration Successful!");
                request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration Failed");
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }

    // Helper method to register user
    private boolean registerUser(UserCredentials user) {
        // Implement user registration logic
        return userDAO.registerUser(user);
    }
}
