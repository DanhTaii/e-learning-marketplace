package vn.edu.nlu.fit.elearning.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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
        Course courseMostPopular = courseService.getCoursesMostPopular();
        List<Course> coursesLiked = courseService.getThreeCoursesWereLiked();
        List<Course> coursesLastest = courseService.getSixCoursesLast();
        List<Course> coursesFeature = courseService.getSixCoursesMostPopular();

        request.setAttribute("courseMostPopular", courseMostPopular);
        request.setAttribute("coursesLiked", coursesLiked);
        request.setAttribute("coursesLastest", coursesLastest);
        request.setAttribute("coursesFeature", coursesFeature);

        // xử lý cho phần wishlist
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userId") != null) {
            int userId = (Integer) session.getAttribute("userId");

            List<Course> wishlistCourses = wishlistService.getWishlistCourses(userId);
            Set<Integer> wishlistCourseIds = new HashSet<>();
            if (wishlistCourses != null) {
                for (Course c : wishlistCourses) {
                    wishlistCourseIds.add(c.getId()); // courseId
                }
            }


            // Đánh dấu trạng thái wishlist cho tất cả course hiển thị
            markWishlistStatus(coursesLiked, wishlistCourseIds);
            markWishlistStatus(coursesLastest, wishlistCourseIds);
            markWishlistStatus(coursesFeature, wishlistCourseIds);
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void markWishlistStatus(List<Course> courses, Set<Integer> wishlistCourseIds) {
        if (courses != null) {
            for (Course course : courses) {
                if (course != null) {
                    course.setInWishlist(wishlistCourseIds.contains(course.getId()));
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}