package vn.edu.nlu.fit.elearning.feature.review.service;

import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDao;
import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDaoImpl;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao rd;

    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.rd = reviewDao;
    }

    @Override
    public int createReview(ReviewDto review) {
        // TODO: Implement creation logic
        return rd.create(review);
    }

    @Override
    public List<ReviewDto> getReviewsByCourseId(int courseId) {
        return rd.findByCourseId(courseId);
    }

}