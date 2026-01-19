package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.AccessTokenService;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckMailController", value = "/check-email")
public class CheckMailController extends HttpServlet {

    private UserService userService;
    private AccessTokenService accessTokenService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        this.accessTokenService = new AccessTokenService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        // Bảo vệ: phải có email trong session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("resetEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/forgot-password");
            return;
        }

        request.getRequestDispatcher("/html-authentication/check-email.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("resetEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/forgot-password");
            return;
        }


        String email = (String) session.getAttribute("resetEmail");
        String otp = request.getParameter("otp");

        if (otp == null || otp.trim().length() != 5) {
            request.setAttribute("error", "Vui lòng nhập đúng mã 5 chữ số!");
            request.getRequestDispatcher("/html-authentication/check-email.jsp").forward(request, response);
            return;
        }

        User user = userService.getUserByEmail(email);
        if (user == null) {
            request.setAttribute("error", "Không tìm thấy tài khoản!");
            request.getRequestDispatcher("/html-authentication/check-email.jsp").forward(request, response);
            return;
        }

        boolean isValid = accessTokenService.validateResetToken(user.getId(), otp);

        if (isValid) {
            accessTokenService.markAsUsed(otp);
            session.setAttribute("resetUserId", user.getId());
            response.sendRedirect(request.getContextPath() + "/reset-password");
        } else {
            request.setAttribute("error", "Mã xác thực không đúng hoặc đã hết hạn!");
            request.getRequestDispatcher("/html-authentication/check-email.jsp").forward(request, response);
        }
    }
}