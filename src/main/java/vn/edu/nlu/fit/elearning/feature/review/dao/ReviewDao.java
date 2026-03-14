package vn.edu.nlu.fit.elearning.feature.review.dao;

import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;

import java.util.List;

public interface ReviewDao {
    //    @Override
    List<ReviewDto> findAll();

    int create(ReviewDto entity);

    List<ReviewDto> findByCourseId(int courseId);
}
