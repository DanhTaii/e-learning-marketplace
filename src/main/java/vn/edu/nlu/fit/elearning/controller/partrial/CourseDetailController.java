package vn.edu.nlu.fit.elearning.controller.partrial;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.dto.TagDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourDetailController", value = "/course-detail")
public class CourseDetailController extends HttpServlet {

    private CourseService cs;
    private ReviewService reviewService;
    private LessonService lessonService;
    private TagService tagService;
    private CategoryService categoryService;
    private EnrollmentService enrollmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = new CourseService();
        this.lessonService = new LessonService();
        this.reviewService = new ReviewService();
        this.tagService = new TagService();
        this.categoryService = new CategoryService();
        this.enrollmentService = new EnrollmentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);

        String userIdStr = request.getParameter("userId");
        if (userIdStr != null) {
            userId = Integer.parseInt(userIdStr);
        }
        CourseDetailDto c = cs.getCourse(id, userId);

        // này làm cho reviews
        List<ReviewDto> reviewDtos = reviewService.getReviewsByCourseId(id);
        request.setAttribute("reviewDtos", reviewDtos);

        // này làm cho lessons
        List<Lesson> lessons = lessonService.getLessonsByCourseId(id);
        request.setAttribute("lessons", lessons);

        // này làm cho tags
        List<TagDto> tags = tagService.getTagsByCourseId(id);
        request.setAttribute("tags", tags);

        // này làm cho category
        Category category = categoryService.getCategoryById(id);
        request.setAttribute("category", category);
        CategoryDto category2 = categoryService.getCategoryByCourseId(id);
        request.setAttribute("category2", category2);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        request.setAttribute("c", c);
        request.getRequestDispatcher("/html-partrial/course-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}