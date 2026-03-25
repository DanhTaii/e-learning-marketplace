package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.auth.dto.LoginRequestDto;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.user.dto.UserShortDto;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/sign-in")
public class LoginController extends HttpServlet {

    private AuthService authService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        try {
            UserShortDto canLogin = authService.login(new LoginRequestDto(email, pass));
            if (canLogin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", canLogin.getId());
                session.setAttribute("userSession", canLogin);

                if (canLogin.getRole() == Role.ADMIN) {
                    request.getSession().setAttribute("flashSuccess", "Đăng nhập thành công!");
                    response.sendRedirect("admin/dashboard");
                    return;
                } else {
                    request.getSession().setAttribute("flashSuccess", "Đăng nhập thành công!");
                    response.sendRedirect("index");
                    return;
                }
            } else {
                request.setAttribute("error", "Bạn nhập sai email hoặc mật khẩu!");
                doGet(request, response);
                return;
            }
        } catch (IllegalArgumentException e) {
            // Bắt các lỗi nghiệp vụ (Trống thông tin, sai tài khoản...)
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
            return;
        } catch (Exception e) {
            // Lỗi hệ thống (DB sập, NullPointer...)
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi hệ thống xảy ra, vui lòng thử lại sau!");
            doGet(request, response);
            return;
        }

    }
}