package vn.edu.nlu.fit.elearning.feature.google.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.google.service.GoogleConstants;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthServiceImpl;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.google.model.GoogleUser;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.google.service.GoogleUtils;

import java.io.IOException;

@WebServlet(name = "LoginGoogleController", value = "/sign-in/google")
public class LoginGoogleController extends HttpServlet {

    private AuthService AuthService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.AuthService = new AuthServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code"); // Mã code từ Google trả về

        if (code == null || code.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/sign-in?error=failed");
            return;
        }

        // Lấy Redirect URI đúng cho môi trường hiện tại
        String currentRedirectUri = GoogleConstants.getRedirectUri();

        // Đổi mã code lấy token
        String accessToken = GoogleUtils.getToken(code, currentRedirectUri);

        // Bước 2: Dùng Access Token lấy thông tin User (Email, Tên, Avatar)
        GoogleUser googleUser = GoogleUtils.getUserInfo(accessToken);

        User user = AuthService.processSocialLogin(googleUser);

        HttpSession session = request.getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userId", user.getId());

        response.sendRedirect(request.getContextPath() + "/index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}