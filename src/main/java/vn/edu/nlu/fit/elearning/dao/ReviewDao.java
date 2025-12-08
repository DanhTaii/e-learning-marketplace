package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Review;

import java.util.List;

public class ReviewDao extends BaseDao implements BaseCrudDao<Review, Integer> {

    @Override
    public void create(Review entity) {
        // TODO: Implement create logic
    }

    @Override
    public Review findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<Review> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT r.id AS review_id, r.user_id, u.first_name, u.last_name, u.email, r.course_id, c.title AS course_title, r.rating, r.comment, r.created_at\n" +
                    "FROM Reviews r\n" +
                    "JOIN Users u ON r.user_id = u.id\n" +
                    "JOIN Courses c ON r.course_id = c.id\n" +
                    "ORDER BY r.created_at DESC;\n").mapToBean(Review.class).list();
        });
    }

    @Override
    public int update(Review entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }
}