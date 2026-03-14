package vn.edu.nlu.fit.elearning.feature.review.service;

import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    int createReview(ReviewDto review);

    List<ReviewDto> getReviewsByCourseId(int courseId);
}
