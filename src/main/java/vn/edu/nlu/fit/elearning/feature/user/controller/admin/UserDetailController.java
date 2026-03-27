package vn.edu.nlu.fit.elearning.feature.user.controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.user.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends HttpServlet {
    private transient UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = BeanContainer.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập mã hóa đầu ra
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        UserDetailResponse user = userService.getUserById(id);

        if (user != null) {
            //Đưa dữ liệu người dùng về dạng JSON cho bên JavaScript đọc và hiển thị
            String json = new Gson().toJson(user);
            response.getWriter().write(json);
        } else {
            response.setStatus(404); // Không tìm thấy User trong DB
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        String statusStr = request.getParameter("status");
        BaseStatus status = BaseStatus.valueOf(statusStr.toUpperCase());

        UserRoleStatusRequest req = new UserRoleStatusRequest();
        req.setRole(Role.valueOf(role));
        req.setStatus(status);

        if (userService.updateRole(userId, req) > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }

    }

}