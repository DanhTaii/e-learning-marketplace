package vn.edu.nlu.fit.elearning.controller.personal.setting;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

@WebServlet(name = "AccountProfileController", value = "/personal/account-profile")
public class AccountProfileController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userSession = null;

        if (session != null) {
            userSession = (User) session.getAttribute("userSession");
        }
        request.setAttribute("currentPage", "profile");
        request.getRequestDispatcher("/html-personal/account-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userSession = (User) session.getAttribute("userSession");
        String phone = request.getParameter("phone");
        String name = request.getParameter("username");
        try {
            // Gọi Service để xử lý
            boolean isSuccess = userService.updateUserProfile(userSession, name, phone);

            if (isSuccess) {
                session.setAttribute("userSession", userSession);
                session.setAttribute("flashSuccess", "Cập nhật thông tin thành công!");
            } else {
                session.setAttribute("flashError", "Cập nhật thất bại, vui lòng thử lại.");
            }

        } catch (IllegalArgumentException e) {
            session.setAttribute("flashError", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("flashError", "Đã xảy ra lỗi hệ thống.");
        }

        // Chuyển hướng lại trang profile để hiển thị thông báo
        response.sendRedirect(request.getContextPath() + "/personal/account-profile");

    }
}