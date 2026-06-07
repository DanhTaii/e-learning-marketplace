package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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
        if (filePart == null || filePart.getSize() == 0) {
            request.getSession().setAttribute("flashError", "Vui lòng chọn file Excel!");
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }

        List<String> errorMessages = new ArrayList<>();
        try (InputStream input = filePart.getInputStream()) {
            List<User> users = userAdminService.importUsersFromExcel(input, errorMessages);

            if (!errorMessages.isEmpty()) {
                request.getSession().setAttribute("importErrors", errorMessages);
            }

            if (users == null || users.isEmpty()) {
                request.getSession().setAttribute("flashError", "Không có dữ liệu hợp lệ để import!");

            } else {
                int inserted = userAdminService.createListUsers(users);
                if (errorMessages.isEmpty()) {
                    request.getSession().setAttribute("flashSuccess", "Tải lên " + inserted + " người dùng thành công!");
                } else {
                    request.getSession().setAttribute("flashSuccess", "Đã import " + inserted + " người dùng, một số dòng bị bỏ qua."
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Có lỗi xảy ra khi import file Excel!");
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}