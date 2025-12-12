package vn.edu.nlu.fit.elearning.controller.admin.user_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserSearchController", value = "/UserSearchController")
public class UserSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameSearch");
        String phone = request.getParameter("phoneSearch");
        String role = request.getParameter("roleSearch");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}