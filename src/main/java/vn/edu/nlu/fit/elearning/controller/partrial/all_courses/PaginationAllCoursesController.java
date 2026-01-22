package vn.edu.nlu.fit.elearning.controller.partrial.all_courses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationAllCoursesController", value = "/pagination-all-courses")
public class PaginationAllCoursesController extends HttpServlet {

    private static final int PAGE_SIZE = 16;
    private CourseService courseService;

    @Override
    public void init() {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);

        // Lấy tất cả các tham số filter
        String pageStr      = request.getParameter("page");
        String categoryStr  = request.getParameter("category");
        String sortPrice    = request.getParameter("sortPrice");
        String popular      = request.getParameter("popular");   // "true" nếu phổ biến
        String newest       = request.getParameter("newest");    // tạm thời giữ, sau có thể bỏ

        int page = 1;
        try {
            if (pageStr != null) page = Integer.parseInt(pageStr);
            if (page < 1) page = 1;
        } catch (Exception e) {
            page = 1;
        }

        // Chuyển đổi category
        Integer categoryId = null;
        if (categoryStr != null && !categoryStr.trim().isEmpty()) {
            try {
                categoryId = Integer.parseInt(categoryStr);
            } catch (NumberFormatException ignored) {}
        }

        List<CourseCardDto> listCourse;
        int totalCourses;

        // Dùng filter thống nhất cho mọi trường hợp
        listCourse = courseService.filterCourses(
                categoryId,     // null nếu không lọc cate
                null,           // tagId (chưa dùng)
                null,           // search title
                sortPrice,      // asc/desc hoặc null
                null,           // level
                null,           // priceRange
                null,           // rating
                null,           // duration
                popular,        // "true" nếu phổ biến
                PAGE_SIZE,
                (page - 1) * PAGE_SIZE
        );

        totalCourses = courseService.countFilteredCourses(
                categoryId, null, null, sortPrice, null, null, null, null, popular
        );

        int totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);

        // Set attributes
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCourses", totalCourses);

        // Giữ trạng thái filter cho JSP
        request.setAttribute("category", categoryStr);
        request.setAttribute("sortPrice", sortPrice);
        request.setAttribute("popular", popular);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        request.setAttribute("categories", categoryService.getAllCategories());

        request.getRequestDispatcher("/html-partrial/all-course.jsp").forward(request, response);
    }
}
