package vn.edu.nlu.fit.elearning.feature.review.service;

import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDao;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;

import java.util.List;

public class ReviewService {

    private ReviewDao rd;

    public ReviewService() {
        this.rd = new ReviewDao();
    }

    public int createReview(ReviewDto review) {
        // TODO: Implement creation logic
        return rd.create(review);
    }

    public List<ReviewDto> getReviewsByCourseId(int courseId) {
        return rd.findByCourseId(courseId);
    }

}