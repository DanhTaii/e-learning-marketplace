package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.io.IOException;

@WebServlet(name = "UserUpdateController", value = "/admin/user/update")
public class UserUpdateController extends HttpServlet {

    private transient UserService userService;
    private transient UserAdminService userAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = BeanContainer.getBean(UserService.class);
        this.userAdminService = BeanContainer.getBean(UserAdminService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id =0;
        if (idStr == null || idStr.isEmpty()) {
            return;
        }
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String roleStr = request.getParameter("role");
        Role role = Role.valueOf(roleStr.toUpperCase());

        String statusStr = request.getParameter("status");
        BaseStatus status = BaseStatus.valueOf(statusStr.toUpperCase());

        UserRoleStatusRequest req = new UserRoleStatusRequest();
        req.setRole(role);
        req.setStatus(status);

        int result = userAdminService.updateRole(id, req);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}