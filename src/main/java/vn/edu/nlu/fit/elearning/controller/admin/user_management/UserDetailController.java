package vn.edu.nlu.fit.elearning.controller.admin.user_management;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
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

    }
}