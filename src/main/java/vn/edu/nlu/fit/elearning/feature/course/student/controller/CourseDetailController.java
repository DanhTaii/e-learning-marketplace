package vn.edu.nlu.fit.elearning.feature.course.student.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewService;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourDetailController", value = "/course-detail")
public class CourseDetailController extends HttpServlet {

    private CourseAdminService cs;
    private ReviewService reviewService;
    private LessonService lessonService;
    private TagService tagService;
    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = BeanContainer.getBean(CourseAdminService.class);
        this.lessonService = BeanContainer.getBean(LessonService.class);
        this.reviewService = BeanContainer.getBean(ReviewService.class);
        this.tagService = BeanContainer.getBean(TagService.class);
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        String userIdStr = request.getParameter("userId");
        if (userIdStr != null) {
            userId = Integer.parseInt(userIdStr);
        }

        CourseDetailDto c = cs.getCourseDetail(id, userId);

        // này làm cho reviews
        List<ReviewDto> reviewDtos = reviewService.getReviewsByCourseId(id);
        request.setAttribute("reviewDtos", reviewDtos);

        // này làm cho lessons
        List<Lesson> lessons = lessonService.getLessonsByCourseId(id);
        request.setAttribute("lessons", lessons);

        // này làm cho tags
        System.out.println("=============BẮT ĐẦU LẤY DANH SÁCH TAGS THEO KHÓA HỌC");
        List<TagDto> tags = tagService.getTagsByCourseId(id);
        System.out.println("=============KẾT THÚC LẤY DANH SÁCH TAGS THEO KHÓA HỌC");
        System.out.println(tags.size());
        System.out.println(tags.toString());
        request.setAttribute("tagsByCourse", tags);

        // này làm cho category
        Category category = categoryService.getCategoryById(c.getCategoryId());
        request.setAttribute("category", category);

        request.setAttribute("c", c);
        request.getRequestDispatcher("/views/pages/partial/course-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}