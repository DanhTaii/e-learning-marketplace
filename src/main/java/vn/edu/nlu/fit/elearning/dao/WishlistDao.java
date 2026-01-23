package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Course;

import java.util.List;

public class WishlistDao extends BaseDao {


    public int delete(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
                    return handle.createUpdate("DELETE FROM wishlist WHERE user_id = :id AND  course_id = :courseId")
                            .bind("id", userId)
                            .bind("courseId", courseId)
                            .execute();
                }

        );
    }

    // Thêm vào wishlist
    public int addWishlist(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
                    return handle.createUpdate("INSERT INTO wishlist (user_id, course_id, added_at) VALUES (:userId, :courseId, CURRENT_TIMESTAMP)")
                            .bind("userId", userId)
                            .bind("courseId", courseId)
                            .execute();
                }
        );
    }

    // Kiểm tra tồn tại
    public boolean exists(int userId, int courseId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT 1 FROM wishlist WHERE user_id = :userId AND course_id = :courseId")
                        .bind("userId", userId).bind("courseId", courseId).mapTo(Integer.class).findOne().isPresent()
        );
    }

    // Lấy danh sách course trong wishlist
    public List<CourseCardDto> findWishlistCoursesByUser(int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.thumbnail_url, c.level,SUM(l.duration_minutes) / 60.0 AS duration_hours," +
                            "c.author_name, c.price, c.discount_price, AVG(r.rating) AS avgRating,\n" +
                            "COUNT(DISTINCT e.id) AS student_count,\n" +
                            "(CASE WHEN :userId IS NOT NULL AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, " +
                            "(CASE WHEN :userId IS NOT NULL AND w_user.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist\n" +
                            "FROM courses c \n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w_user ON w_user.course_id = c.id AND w_user.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE AND w_user.user_id = :userId\n" +
                            "GROUP BY c.id, w_user.user_id\n")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }


}