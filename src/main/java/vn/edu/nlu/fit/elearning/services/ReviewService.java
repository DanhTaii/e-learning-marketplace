package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.ReviewDao;
import vn.edu.nlu.fit.elearning.model.Review;

import java.util.List;

public class ReviewService {

    private ReviewDao rd;

    public ReviewService() {
        this.rd = new ReviewDao();
    }

    public int createReview(Review review) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<Review> getAllReviews() {
        // TODO: Implement getAll logic
        return rd.findAll();
    }

    public Review getReviewById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateReview(Review review) {
        // TODO: Implement update logic
    }

    public void deleteReview(int id) {
        // TODO: Implement delete logic
    }
}