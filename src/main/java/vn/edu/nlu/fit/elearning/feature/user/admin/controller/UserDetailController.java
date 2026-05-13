package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends BaseController {

    private UserAdminService userAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userAdminService = BeanContainer.getBean(UserAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = RequestUtils.getParameterAsInt(request, "id", -1);
                UserAdminDto user = userAdminService.getUserById(id);
                if (user != null) {
                    request.setAttribute("user", user);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy người dùng!");
                    return;
                }
            }

            this.forward(request, response, "/views/pages/admin/user/user-create.jsp"
            );

        } catch (Exception e) {
            log("Unexpected error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            BaseStatus status = BaseStatus.valueOf(request.getParameter("status"));
            int result = userAdminService.updateUserRoleAndStatus(id, roleId, status);

            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật người dùng thành công!");

            } else {
                request.getSession().setAttribute("flashError", "Cập nhật thất bại!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/users");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Lỗi hệ thống!");
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }
}