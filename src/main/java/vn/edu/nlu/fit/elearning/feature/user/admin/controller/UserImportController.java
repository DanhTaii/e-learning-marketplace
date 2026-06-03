package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserImportController", value = "/admin/user/import/excel")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class UserImportController extends HttpServlet {

    private transient UserAdminService userAdminService;

    @Override
    public void init() {
        this.userAdminService =
                BeanContainer.getBean(UserAdminService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = request.getPart("excelFile");
        List<String> errorMessages = new ArrayList<>();

        try (InputStream input = filePart.getInputStream()) {
            List<User> users = userAdminService.importUsersFromExcel(input, errorMessages);

            if (users != null && !users.isEmpty()) {
                userAdminService.createListUsers(users);
                request.getSession().setAttribute("flashSuccess", "Tải lên " + users.size() + " người dùng thành công!");
            } else {
                request.getSession().setAttribute("flashError", "File excel không hợp lệ");
            }

            if (!errorMessages.isEmpty()) {
                request.getSession().setAttribute("importErrors", errorMessages
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Có lỗi xảy ra khi import file");
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}