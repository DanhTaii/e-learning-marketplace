package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.utils.security.HashUtils;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseService;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends BaseController {

    private UserAdminService userAdminService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userAdminService = BeanContainer.getBean(UserAdminService.class);
        this.courseService = BeanContainer.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = RequestUtils.getParameterAsInt(request, "id", -1);
                UserAdminDto user = userAdminService.getUserById(id);
                if (user != null) {
                    user.setCourses(courseService.getCoursesByUserId(id));
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idRaw = request.getParameter("id");
            // update
            if (idRaw != null && !idRaw.isEmpty()) {

                int id = Integer.parseInt(idRaw);
                int roleId = Integer.parseInt(request.getParameter("roleId"));

                BaseStatus status = BaseStatus.valueOf(request.getParameter("status"));

                int result = userAdminService.updateUserRoleAndStatus(id, roleId, status);

                if (result > 0) {
                    request.getSession().setAttribute("flashSuccess", "Cập nhật người dùng thành công!");
                } else {
                    request.getSession().setAttribute("flashError", "Cập nhật thất bại!");
                }
                response.sendRedirect(request.getContextPath() + "/admin/users");
                return;
            }

            // create
            UserAdminDto user = new UserAdminDto();

            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setPhone(request.getParameter("phone"));

            user.setPassword(HashUtils.hashpassword(request.getParameter("password")));
            user.setConfirmPassword(request.getParameter("confirmPassword"));

            user.setRoleId(Integer.parseInt(request.getParameter("roleId")));

            user.setStatus(BaseStatus.valueOf(request.getParameter("status")));
            user.setAvatarUrl(request.getParameter("avatarUrl"));
            int result = userAdminService.createUser(user);
            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Tạo người dùng thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/users");
            } else {
                request.getSession().setAttribute("flashError", "Tạo người dùng thất bại!");
                response.sendRedirect(request.getContextPath() + "/admin/user/detail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Lỗi hệ thống!");
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }
}