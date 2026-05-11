package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserSearchController", value = "/admin/users/search")
public class UserSearchController extends HttpServlet {
    private UserAdminService userAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userAdminService = BeanContainer.getBean(UserAdminService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameSearch");
        String phone = request.getParameter("phoneSearch");
        String role = request.getParameter("roleSearch");
        String dateFrom = request.getParameter("dateFrom");

        List<User> searchUsers = userAdminService.getAllUsersByFilter(username, phone, dateFrom, role);
        request.setAttribute("listUsers", searchUsers);
        request.getRequestDispatcher("/views/pages/admin/user-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}