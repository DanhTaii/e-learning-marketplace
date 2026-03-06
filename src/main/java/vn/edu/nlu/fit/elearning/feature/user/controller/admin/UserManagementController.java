package vn.edu.nlu.fit.elearning.feature.user.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManagementController", value = "/admin/users")
public class UserManagementController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUsers = userService.getAllUsers();
        request.setAttribute("listUsers", listUsers);
        request.setAttribute("currentPage", "users");
        request.getRequestDispatcher("/html-admin/users-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}