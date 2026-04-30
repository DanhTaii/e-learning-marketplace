package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.validator.login.SignInValidator;
import vn.edu.nlu.fit.elearning.feature.auth.dto.LoginRequestDto;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "LoginController", value = "/sign-in")
public class LoginController extends HttpServlet {

    private transient AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.authService = BeanContainer.getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/pages/auth/sign-in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        Map<String, String> errors = SignInValidator.validate(email, pass);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/views/pages/auth/sign-in.jsp").forward(request, response);
            return;
        }

        try {
            UserShortResponse canLogin = authService.login(new LoginRequestDto(email, pass));

            if (canLogin != null) {
                HttpSession session = request.getSession();
                Set<String> userRoles = authService.getUserRoles(canLogin.getId());
                Set<String> userPermissions = authService.getUserPermissions(canLogin.getId());
                session.setAttribute("userPermissions", userPermissions);
                session.setAttribute("userId", canLogin.getId());
                session.setAttribute("userSession", canLogin);

                request.getSession().setAttribute("flashSuccess", "Đăng nhập thành công!");

                if (userPermissions.contains("DASHBOARD_VIEW") && (userRoles.contains("SUPER_ADMIN") || userRoles.contains("ADMIN_ORDER"))) {
                    response.sendRedirect("admin/dashboard");
                    return;
                }
                if (userPermissions.contains("COURSE_VIEW") && userRoles.contains("ADMIN_COURSE")) {
                    response.sendRedirect("admin/courses");
                    return;
                }
                if (userPermissions.contains("USER_VIEW") && userRoles.contains("ADMIN_USER")) {
                    response.sendRedirect("admin/users");
                    return;
                }
                else {
                    response.sendRedirect("index");
                    return;
                }

            } else {
                request.setAttribute("error", "Bạn nhập sai email hoặc mật khẩu!");
                doGet(request, response);
            }

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            logger.error("Errorrrr", e);
            doGet(request, response);
            return;
        } catch (Exception e) {
            request.setAttribute("error", "Có lỗi hệ thống xảy ra, vui lòng thử lại sau!");
            logger.error("Errorrrr", e);
            doGet(request, response);
            return;
        }
    }
}