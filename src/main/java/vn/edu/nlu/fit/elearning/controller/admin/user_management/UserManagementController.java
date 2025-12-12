package vn.edu.nlu.fit.elearning.controller.admin.user_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManagementController", value = "/admin/users")
public class UserManagementController extends HttpServlet {
    private UserService userService;

    public UserManagementController() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUsers = userService.getAllUsers();
        System.out.println(listUsers);
        request.setAttribute("listUsers", listUsers);
        request.getRequestDispatcher("/html-admin/users-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}