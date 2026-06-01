package vn.edu.nlu.fit.elearning.feature.review.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.SessionUtils;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewService;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;

import java.io.IOException;

@WebServlet(name = "CreateReviewController", value = "/my-course/review/create")
public class CreateReviewController extends HttpServlet {
    private ReviewService reviewService;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CreateReviewController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.reviewService = BeanContainer.getBean(ReviewService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this endpoint.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String comment = request.getParameter("comment");
            double rating = Double.parseDouble(request.getParameter("rating"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            int userId = SessionUtils.getCurrentUserId(request);

            boolean hasReviewed = reviewService.isReviewExist(userId, courseId);

            if (hasReviewed) {
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Bạn đã đánh giá khóa học này rồi!\"}");
                return;
            }

            ReviewDto newReview = new ReviewDto();
            newReview.setUserId(userId);
            newReview.setCourseId(courseId);
            newReview.setComment(comment);
            newReview.setRating(rating);

            // Lưu vào DB
            reviewService.createReview(newReview);

            // Trả về JSON thành công
            response.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            // Trả về JSON thất bại nếu có lỗi
            response.getWriter().write("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
            logger.error("Error creating review: ", e);
        }
    }
}