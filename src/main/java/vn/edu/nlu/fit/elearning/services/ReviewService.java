package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.ReviewDao;
import vn.edu.nlu.fit.elearning.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.model.Review;

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