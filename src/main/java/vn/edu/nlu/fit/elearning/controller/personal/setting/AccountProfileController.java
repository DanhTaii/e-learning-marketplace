package vn.edu.nlu.fit.elearning.controller.personal.setting;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;

import java.io.IOException;

@WebServlet(name = "AccountProfileController", value = "/personal/account-profile")
public class AccountProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userSession = null;

        if (session != null) {
            userSession = (User) session.getAttribute("userSession");
        }
        request.setAttribute("currentPage", "profile");
        request.getRequestDispatcher("/html-personal/account-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}