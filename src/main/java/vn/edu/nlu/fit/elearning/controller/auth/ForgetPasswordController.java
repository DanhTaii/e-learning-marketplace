package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.model.AccessToken;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.AccessTokenService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

@WebServlet(name = "ForgetPasswordController", value = "/forget-password")
public class ForgetPasswordController extends HttpServlet {
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println("Email nhận được từ form: " + email);

        User user = userService.getUserByEmail(email);
        System.out.println("User tìm được: " + (user == null ? "NULL" : user.getUsername()));

        if (user == null) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("html-authentication/forgot-password.jsp").forward(request, response);
        }

        AccessTokenService accessTokenService = new AccessTokenService();
        String token = accessTokenService.generateToken();
        System.out.println("Token được tạo: " + token);


//        String tokenReset = "http://localhost:8080/e_learning_war_exploded/check-mail" + token;
        System.out.println("Link reset password: " + token);

        AccessToken accessToken = null;
        if(user != null){
            accessToken = new AccessToken(user.getId(), token, accessTokenService.expireDateTime(), false);

        }

        AccessTokenDao tokenDao = new AccessTokenDao();
        boolean isCreate = false;
        if ( accessToken != null){
            isCreate = tokenDao.createToken(accessToken);
        }
        if (!isCreate) {
            request.setAttribute("error", "Lỗi server");
            request.getRequestDispatcher("html-authentication/forgot-password.jsp").forward(request, response);
            return;
        }

        boolean isSend = accessTokenService.sendEmail(email, token, user.getUsername(), false);
        if(!isSend){
            request.setAttribute("error", "Gửi không thành công!");
            request.getRequestDispatcher("html-authentication/forgot-password.jsp").forward(request, response);
            return;
        }

        request.setAttribute("success", "Gửi thành công!");
        response.sendRedirect(request.getContextPath() + "/html-authentication/check-email.jsp");
    }
}