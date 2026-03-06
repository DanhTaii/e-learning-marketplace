package vn.edu.nlu.fit.elearning.feature.review.dao;

import vn.edu.nlu.fit.elearning.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;

import java.util.List;

public class ReviewDao extends BaseDao {

//    @Override
    public List<ReviewDto> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT r.id AS review_id, r.user_id, u.first_name, u.last_name, u.email, r.course_id, c.title AS course_title, r.rating, r.comment, r.created_at\n" +
                    "FROM reviews r\n" +
                    "JOIN users u ON r.user_id = u.id\n" +
                    "JOIN courses c ON r.course_id = c.id\n" +
                    "ORDER BY r.created_at DESC;\n").mapToBean(ReviewDto.class).list();
        });
    }

    public int create(ReviewDto entity){
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO reviews(user_id, course_id, rating, comment)\n" +
                    "VALUES (:userId, :courseId, :rating, :comment)")
                    .bindBean(entity)
                    .execute();
        });
    }

    public List<ReviewDto> findByCourseId(int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT r.id, r.user_id, r.course_id, r.rating,\n" +
                    "r.comment,r.created_at,\n" +
                    "u.username AS user_name, u.avatar_url AS thumbnail_url\n" +
                    "FROM reviews r\n" +
                    "JOIN users u ON r.user_id = u.id\n" +
                    "WHERE r.course_id = :course_id\n" +
                    "ORDER BY r.created_at DESC;").bind("course_id",courseId).mapToBean(ReviewDto.class).list();
        });
    }

}