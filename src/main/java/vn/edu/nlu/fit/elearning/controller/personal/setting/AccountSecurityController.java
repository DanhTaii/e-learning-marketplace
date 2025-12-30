package vn.edu.nlu.fit.elearning.controller.personal.setting;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.model.User;

import java.io.IOException;

@WebServlet(name = "AccountSecurityController", value = "/account-security")
public class AccountSecurityController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userSession = null;
        if (session != null) {
            userSession = (User) session.getAttribute("userSession");
        }
        request.setAttribute("currentPage", "security");
        request.getRequestDispatcher("html-personal/account-security.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}