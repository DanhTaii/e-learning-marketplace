package vn.edu.nlu.fit.elearning.feature.enrollment.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.SessionUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;
import vn.edu.nlu.fit.elearning.feature.certificate.service.CertificateService;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "MyCourseDetailController", value = "/personal/my-course/detail")
public class MyCourseDetailController extends HttpServlet {
    private transient EnrollmentService enrollmentService;
    private transient UserLessonProgressService ulp;
    private transient ReviewService reviewService;
    private transient CertificateService certificateService;
    private static final Logger logger = LoggerFactory.getLogger(MyCourseDetailController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
        this.ulp = BeanContainer.getBean(UserLessonProgressService.class);
        this.reviewService = BeanContainer.getBean(ReviewService.class);
        this.certificateService = BeanContainer.getBean(CertificateService.class);
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

        boolean hasCertificate = certificateService.hasCertificate(userId, courseId);

        request.setAttribute("hasCertificate", hasCertificate);
        request.setAttribute("enrollmentDetail", enrollmentDetail);
        request.getRequestDispatcher("/views/pages/personal/course/enrollment/id/course-content.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = SessionUtils.getCurrentUserId(request);

            String lessonIdStr = request.getParameter("lessonId");
            String isCompletedStr = request.getParameter("completed");
            String enrollmentIdString = request.getParameter("enrollmentId");

            int id = Integer.parseInt(lessonIdStr);
            boolean isCompleted = Boolean.parseBoolean(isCompletedStr);

            ulp.updateUserLessonProgress(id, isCompleted);

            int enrollmentId = Integer.parseInt(enrollmentIdString);
            int newPercent = enrollmentService.getNewPercentComplete(enrollmentId);

            int certId = 0;

            if (newPercent == 100 && userId > 0) {
                int courseId = enrollmentService.getCourseIdById(enrollmentId);
                boolean hasCertificate = certificateService.hasCertificate(userId, courseId);

                if (!hasCertificate) {
                    Certificate cert = new Certificate();
                    cert.setCourseId(courseId);
                    cert.setUserId(userId);
                    cert.setIssueDate(new Timestamp(System.currentTimeMillis()));

                    String realPath = request.getServletContext().getRealPath("");

                    certId = certificateService.processAndGenerateCertificate(cert, realPath);
                }

            }
            response.setContentType("application/json");
            PrintWriter result = response.getWriter();
            result.write("{\"status\" : \"success\", \"newPercent\":" + newPercent + ", \"certId\":" + certId + "}");
            result.flush();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}