package vn.edu.nlu.fit.elearning.controller.partrial;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.LessonService;
import vn.edu.nlu.fit.elearning.services.ReviewService;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseDetailController", value = "/course-detail")
public class CourseDetailController extends HttpServlet {

    private CourseService cs;
    private ReviewService reviewService;
    private LessonService lessonService;
    private TagService ts;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = new CourseService();
        this.ts = new TagService();
        this.lessonService = new LessonService();
        this.reviewService = new ReviewService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course c = cs.getCourse(id);
        List<Tag> tags = ts.getAllTags();

        // này làm cho reviews
        List<ReviewDto> reviewDtos = reviewService.getReviewsByCourseId(id);
        request.setAttribute("reviewDtos",reviewDtos);

        // này làm cho lessons
        List<Lesson> lessons = lessonService.getLessonsByCourseId(id);
        request.setAttribute("lessons",lessons);

        request.setAttribute("tags", tags);
//        c.setReviews(reviewDtos);
        request.setAttribute("c", c);
        request.getRequestDispatcher("/html-partrial/course-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}