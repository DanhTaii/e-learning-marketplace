package vn.edu.nlu.fit.elearning.feature.wishlist.dao;

import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.database.BaseDao;

import java.util.List;

public class WishlistDaoImpl extends BaseDao implements WishlistDao {


    @Override
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
    @Override
    public int addWishlist(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
                    return handle.createUpdate("INSERT INTO wishlist (user_id, course_id, added_at) VALUES (:userId, :courseId, CURRENT_TIMESTAMP) ON DUPLICATE KEY UPDATE added_at = CURRENT_TIMESTAMP ")
                            .bind("userId", userId)
                            .bind("courseId", courseId)
                            .execute();
                }
        );
    }

    // Kiểm tra tồn tại
    @Override
    public boolean exists(int userId, int courseId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT 1 FROM wishlist WHERE user_id = :userId AND course_id = :courseId")
                        .bind("userId", userId).bind("courseId", courseId).mapTo(Integer.class).findOne().isPresent()
        );
    }

    // Lấy danh sách course trong wishlist
    @Override
    public List<CourseCardDto> findWishlistCoursesByUser(int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.thumbnail_url, c.level," +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "c.author_name, c.price, c.discount_price, " +
                            "AVG(r.rating) AS avgRating,\n" +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS student_count, " +
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