package vn.edu.nlu.fit.elearning.controller.partrial.allCourses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationAllCoursesController", value = "/pagination-all-courses")
public class PaginationAllCoursesController extends HttpServlet {

    private static final int PAGE_SIZE = 4; // giữ nguyên 4 nếu bạn muốn, hoặc tăng lên 8/12
    private CourseService courseService;
    private CategoryService categoryService;

    @Override
    public void init() {
        courseService = new CourseService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        request.setAttribute("categories", categoryService.getAllCategories());

        request.getRequestDispatcher("/html-partrial/all-course.jsp").forward(request, response);
    }
}
