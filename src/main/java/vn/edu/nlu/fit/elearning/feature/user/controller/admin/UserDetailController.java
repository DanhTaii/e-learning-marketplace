package vn.edu.nlu.fit.elearning.feature.user.controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends HttpServlet {
    private UserService userService;

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
        User user = userService.getUserById(id);

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
        int id = Integer.parseInt(request.getParameter("id"));
//        String email = request.getParameter("email");
//        String username = request.getParameter("username");
        String role = request.getParameter("role");
//        String phone = request.getParameter("phone");

        User user = new User();
        user.setId(id);
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPhone(phone);
        user.setRole(role);

        if (userService.updateUser(user) > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }

    }

}