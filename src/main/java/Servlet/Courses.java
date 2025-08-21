package Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/courses")
public class Courses extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        // If logged in, show courses HTML
        request.getRequestDispatcher("Courses.html").forward(request, response);
    }
}
