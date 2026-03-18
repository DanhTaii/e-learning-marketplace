package vn.edu.nlu.fit.elearning.feature.enrollment.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCourseDetailController", value = "/my-course/detail")
public class MyCourseDetailController extends HttpServlet {
    private EnrollmentService enrollmentService;
    private UserLessonProgressService ulp;
    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
        this.ulp = BeanContainer.getBean(UserLessonProgressService.class);
        this.reviewService = BeanContainer.getBean(ReviewService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        int courseId = Integer.parseInt(request.getParameter("courseId"));

        EnrollmentDetailDto enrollmentDetail = enrollmentService.getEnrollmentDetail(userId, courseId);
        List<ReviewDto> reviewDtos = reviewService.getReviewsByCourseId(courseId);
        enrollmentDetail.setListReviews(reviewDtos);

        List<LessonProgressDTO> listLessons = ulp.getAllUserLessonProgresss(userId, courseId);
        enrollmentDetail.setListLesson(listLessons);

        request.setAttribute("enrollmentDetail", enrollmentDetail);
        request.getRequestDispatcher("/views/pages/personal/course/course-content.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("lessonId");
        String isCompletedStr = request.getParameter("completed");

        int id = Integer.parseInt(idStr);
        boolean isCompleted = Boolean.parseBoolean(isCompletedStr);

        ulp.updateUserLessonProgress(id, isCompleted);

    }
}