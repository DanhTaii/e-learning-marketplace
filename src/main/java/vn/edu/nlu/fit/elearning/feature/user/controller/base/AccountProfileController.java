package vn.edu.nlu.fit.elearning.feature.user.controller.base;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;
import java.util.List;

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

//        HttpSession session = request.getSession();
//        int userId = 0;
//
//        if (session != null && session.getAttribute("userId") != null) {
//            userId = (Integer) session.getAttribute("userId");
//        }

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());
//        UserService userService = new UserService();
//        User user = userService.getUserById(userId);
//        request.setAttribute("user", user);

        request.setAttribute("currentPage", "profile");
        request.getRequestDispatcher("/html-personal/account-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User userSession = (User) session.getAttribute("userSession");
        String phone = request.getParameter("phone");
        String name = request.getParameter("username");
        String avatarUrl = request.getParameter("avatarUrl");
        try {
            // Gọi Service để xử lý
            boolean isSuccess = userService.updateUserProfile(userSession, name, phone, avatarUrl);

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