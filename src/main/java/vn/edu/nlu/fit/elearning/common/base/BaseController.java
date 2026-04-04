package vn.edu.nlu.fit.elearning.common.base;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BaseController extends HttpServlet {

    protected void forward(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        request.getRequestDispatcher(view).forward(request, response);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        response.sendRedirect(request.getContextPath() + url);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            super.service(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("flashError", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
