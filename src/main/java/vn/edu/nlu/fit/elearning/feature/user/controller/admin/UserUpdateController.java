package vn.edu.nlu.fit.elearning.feature.user.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;

@WebServlet(name = "UserUpdateController", value = "/admin/user/update")
public class UserUpdateController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");

        if (userService.updateRole(id,role) > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }

    }
}