package vn.edu.nlu.fit.elearning.controller.admin.user_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

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
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);

        if (userService.updateUser(user) > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }

    }
}