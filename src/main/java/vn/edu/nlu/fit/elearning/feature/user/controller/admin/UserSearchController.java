package vn.edu.nlu.fit.elearning.feature.user.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserSearchController", value = "/admin/users/search")
public class UserSearchController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameSearch");
        String phone = request.getParameter("phoneSearch");
        String role = request.getParameter("roleSearch");
        String dateFrom = request.getParameter("dateFrom");

        List<User> searchUsers = userService.getAllUsersByFilter(username, phone, dateFrom, role);
        request.setAttribute("listUsers", searchUsers);
        request.getRequestDispatcher("/views/pages/admin/users-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}