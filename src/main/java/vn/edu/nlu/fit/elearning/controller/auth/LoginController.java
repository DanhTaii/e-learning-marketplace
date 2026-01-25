package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dao.UserDao;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", value = "/sign-in")
public class LoginController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/html-authentication/sign-in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        try {
            User canLogin = userService.login(email, pass);
            if (canLogin != null) {
                HttpSession session = request.getSession();
                canLogin.setPassword(null);
                session.setAttribute("userId", canLogin.getId());
                session.setAttribute("userSession", canLogin);

                if (canLogin.getRole().equalsIgnoreCase("admin")) {
                    response.sendRedirect("admin/dashboard");
                    return;
                } else {
                    canLogin.setPassword("");
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