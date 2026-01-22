package vn.edu.nlu.fit.elearning.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "IndexController", value = {"/index"})
public class IndexController extends HttpServlet {

    private CourseService courseService;
    private WishlistService wishlistService;

    @Override
    public void init() throws ServletException {
        super.init();
        courseService = new CourseService();
        // do làm session
        // Khởi tạo 1 lần duy nhất
        wishlistService = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TẠO LỖI GIẢ ĐỂ TEST
//        String testNull = null;
//        int length = testNull.length(); // Dòng này chắc chắn ném ra NullPointerException
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        // 1. Category
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        // 2. Banner stats
        UserService userService = new UserService();
        request.setAttribute("totalUsers", userService.totalUsers());
        request.setAttribute("totalCourses", courseService.totalCourses());
        request.setAttribute("avgRating", courseService.avgRating());

        // 3.Tag
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        // 4 Các danh sách khóa học
        CourseCardDto courseMostPopular = courseService.getCoursesMostPopular(userId);
        List<CourseCardDto> coursesLiked = courseService.getThreeCoursesWereLiked(userId);
        List<CourseCardDto> coursesLastest = courseService.getSixCoursesLast(userId);
        List<CourseCardDto> coursesFeature = courseService.getSixCoursesMostPopular(userId);

        request.setAttribute("courseMostPopular", courseMostPopular);
        request.setAttribute("coursesLiked", coursesLiked);
        request.setAttribute("coursesLastest", coursesLastest);
        request.setAttribute("coursesFeature", coursesFeature);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}