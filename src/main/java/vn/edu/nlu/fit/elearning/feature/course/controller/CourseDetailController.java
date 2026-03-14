package vn.edu.nlu.fit.elearning.feature.course.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.category.service.ICategoryService;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseServiceImpl;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonServiceImpl;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewService;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourDetailController", value = "/course-detail")
public class CourseDetailController extends HttpServlet {

    private CourseServiceImpl cs;
    private ReviewService reviewService;
    private LessonService lessonService;
    private TagService tagService;
    private ICategoryService ICategoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = new CourseServiceImpl();
        this.lessonService = new LessonServiceImpl();
        this.reviewService = new ReviewServiceImpl();
        this.tagService = new TagServiceImpl();
        this.ICategoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
//        UserService userService = new UserService();
//        User user = userService.getUserById(userId);
//        request.setAttribute("user", user);

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
        Category category = ICategoryService.getCategoryById(c.getCategoryId());
        request.setAttribute("category", category);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        ICategoryService ICategoryService = new CategoryService();
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);

        request.setAttribute("c", c);
        request.getRequestDispatcher("/views/pages/partial/course-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}