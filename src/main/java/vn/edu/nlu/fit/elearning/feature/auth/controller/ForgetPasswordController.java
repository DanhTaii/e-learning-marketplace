package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;

@WebServlet(name = "ForgetPasswordController", value = "/forgot-password")
public class ForgetPasswordController extends HttpServlet {
    UserService userService;
    AccessTokenService accessTokenService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = BeanContainer.getBean(UserService.class);
        this.accessTokenService = BeanContainer.getBean(AccessTokenService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
//        System.out.println("Email nhận được từ form: " + email);

        UserShortResponse user = userService.getUserByEmail(email);
//        System.out.println("User tìm được: " + (user == null ? "NULL" : user.getUsername()));

        try {


            if (user == null) {
                request.setAttribute("error", "Email không tồn tại!");
                request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);
                return;
            }

            String token = accessTokenService.generateToken();
//        System.out.println("Token được tạo: " + token);


//        String tokenReset = "http://localhost:8080/e_learning_war_exploded/check-mail" + token;
//        System.out.println("Link reset password: " + token);

            AccessToken accessToken = null;
            if (user != null) {
                accessToken = new AccessToken(user.getId(), token, accessTokenService.expireDateTime(), false);

            }


            int isCreate = 0;
            if (accessToken != null) {
                isCreate = accessTokenService.createToken(accessToken);
            }

            if (isCreate == 0) {
                request.setAttribute("error", "Lỗi server");
                request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);
                return;
            }

            boolean isSend = accessTokenService.sendEmail(email, token, user.getUsername());
            if (!isSend) {
                request.setAttribute("error", "Gửi không thành công!");
                request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);
                return;
            }

            // Lưu email vào session để dùng ở check-email
            HttpSession session = request.getSession();
            session.setAttribute("resetEmail", email);
            session.setMaxInactiveInterval(10 * 60); // 10 phút

            request.setAttribute("success", "Gửi thành công!");
            response.sendRedirect(request.getContextPath() + "/check-email");
        } catch (Exception e) {
            // In ra log để bạn nhìn thấy trên Render ngay
            e.printStackTrace(System.err);

            // Ném ra để Tomcat Wrapper bắt được và tạo ra cái log SEVERE [http-nio-8080-exec-...]
            throw new ServletException("Lỗi tại ForgetPasswordController", e);
        }
    }
}