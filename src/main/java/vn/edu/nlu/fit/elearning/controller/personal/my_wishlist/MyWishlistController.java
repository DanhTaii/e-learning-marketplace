package vn.edu.nlu.fit.elearning.controller.personal.my_wishlist;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;
import vn.edu.nlu.fit.elearning.services.WishlistService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyWishlistController", value = "/personal/my-wishlist")
public class MyWishlistController extends HttpServlet {

    private WishlistService ws;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ws = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        int userId = (int) session.getAttribute("userId");

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);

        // Nếu không có courseId thì hiển thị danh sách wishlist
        List<CourseCardDto> wishlistCourses = ws.getWishlistCourses(userId);
        request.setAttribute("wishlistCourses", wishlistCourses);
        request.getRequestDispatcher("/html-personal/my-wishlist.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(401); // Unauthorized
            return;
        }
        int userId = (int) session.getAttribute("userId");
        System.out.println(userId);
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        boolean added = ws.toggleWishlist(userId, courseId);

        response.getWriter().write(added ? "added" : "removed");
    }
}