package vn.edu.nlu.fit.elearning.feature.review.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.reviewService = BeanContainer.getBean(ReviewService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        double rating = Double.parseDouble(request.getParameter("rating"));

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int userId = SessionUtils.getCurrentUserId(request);
        ReviewDto newReview = new ReviewDto();

        newReview.setUserId(userId);
        newReview.setCourseId(courseId);
        newReview.setComment(comment);
        newReview.setRating(rating);
        reviewService.createReview(newReview);

        response.sendRedirect(request.getContextPath() + "/personal/my-course/detail?courseId=" + courseId);
    }
}