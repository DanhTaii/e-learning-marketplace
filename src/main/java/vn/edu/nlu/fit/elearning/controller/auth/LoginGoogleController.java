package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.GoogleUser;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.UserService;
import vn.edu.nlu.fit.elearning.utils.GoogleUtils;

import java.io.IOException;

@WebServlet(name = "LoginGoogleController", value = "/sign-in/google")
public class LoginGoogleController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code"); // Mã code từ Google trả về

        if (code == null || code.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/sign-in?error=failed");
            return;
        }

        // Bước 1: Gọi hàm để đổi mã code lấy Access Token
        String accessToken = GoogleUtils.getToken(code);

        // Bước 2: Dùng Access Token lấy thông tin User (Email, Tên, Avatar)
        GoogleUser googleUser = GoogleUtils.getUserInfo(accessToken);

        User user = userService.processSocialLogin(googleUser);

        HttpSession session = request.getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userId", user.getId());

        response.sendRedirect(request.getContextPath() + "/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}