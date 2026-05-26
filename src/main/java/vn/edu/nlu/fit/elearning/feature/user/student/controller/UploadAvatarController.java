package vn.edu.nlu.fit.elearning.feature.user.student.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.external.cloudinary.CloudinaryService;

import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.io.IOException;

@WebServlet("/personal/upload-avatar")
@MultipartConfig
public class UploadAvatarController extends BaseController {

    private transient UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = BeanContainer.getBean(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            UserShortResponse currentUser = (UserShortResponse) request.getSession().getAttribute("userSession");
            System.out.println(currentUser);

            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/personal/account-profile");
                return;
            }

            Part avatarPart = request.getPart("avatar");

            System.out.println(avatarPart);

            if (avatarPart != null && avatarPart.getSize() > 0) {

                String avatarUrl = CloudinaryService.uploadFile(avatarPart, "elearning/user/avatar");

                boolean success = userService.updateAvatar(currentUser.getId(), avatarUrl);

                if (success) {
                    currentUser.setAvatarUrl(avatarUrl);
                    request.getSession().setAttribute("userProfile", currentUser);
                    request.getSession().setAttribute("flashSuccess", "Cập nhật avatar thành công!");

                } else {
                    request.getSession().setAttribute("flashError", "Cập nhật avatar thất bại!");
                }
            }

            response.sendRedirect(request.getContextPath() + "/personal/account-profile");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Lỗi hệ thống!");
            response.sendRedirect(request.getContextPath() + "/personal/account-profile");
        }
    }
}