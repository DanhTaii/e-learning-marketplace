package vn.edu.nlu.fit.elearning.feature.user.student.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserProfileRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserProfileResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.io.IOException;

@WebServlet(name = "AccountProfileController", value = "/personal/account-profile")
public class AccountProfileController extends HttpServlet {
    private transient UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = BeanContainer.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userIdStr = session.getAttribute("userId") != null ? session.getAttribute("userId").toString() : null;

        if (userIdStr == null || userIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);

            UserProfileResponse userProfile = userService.getProfileById(userId);
            request.setAttribute("userProfile", userProfile);
            request.setAttribute("currentPage", "profile");
            request.getRequestDispatcher("/views/pages/personal/profile/account-profile.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid User ID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute("userId");

        UserProfileRequest profileRequest = new UserProfileRequest();
        String phone = request.getParameter("phone");
        String name = request.getParameter("username");
        String avatarUrl = request.getParameter("avatarUrl");
        profileRequest.setPhone(phone);
        profileRequest.setUsername(name);
        profileRequest.setAvatarUrl(avatarUrl);
        try {
            // Gọi Service để xử lý
            boolean isSuccess = userService.updateUserProfile(userId, profileRequest);

            if (isSuccess) {
//                session.setAttribute("userSession", userSession);
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