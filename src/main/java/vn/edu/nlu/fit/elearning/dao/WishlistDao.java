package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Course;

import java.util.List;

public class WishlistDao extends BaseDao {


    public int delete(Integer id) {
        return getJdbi().withHandle(handle -> {
                    return handle.createUpdate("DELETE FROM wishlist WHERE id = :id").bind("id", id).execute();
                }

        );
    }

    // Thêm vào wishlist
    public boolean addWishlist(int userId, int courseId) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO Wishlist (user_id, course_id, added_at) VALUES (:userId, :courseId, CURRENT_TIMESTAMP)").bind("userId", userId).bind("courseId", courseId).execute() > 0
        );
    }

    // Kiểm tra tồn tại
    public boolean exists(int userId, int courseId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT 1 FROM Wishlist WHERE user_id = :userId AND course_id = :courseId")
                        .bind("userId", userId).bind("courseId", courseId).mapTo(Integer.class).findOne().isPresent()
        );
    }

    // Lấy danh sách course trong wishlist
    public List<Course> findWishlistCoursesByUser(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT w.id AS wishlistId, " +
                                        "c.id, " +
                                        "c.title, " +
                                        "c.thumbnail_url AS thumbnailUrl, " +
                                        "c.level, " +
                                        "c.author_name AS authorName, " +
                                        "c.price, " +
                                        "c.discount_price AS discountPrice, " +
                                        "COALESCE(SUM(l.duration_minutes), 0) / 60.0 AS durationHours " +
                                        "FROM Wishlist w " +
                                        "JOIN Courses c ON w.course_id = c.id " +
                                        "LEFT JOIN lessons l ON l.course_id = c.id " +
                                        "WHERE w.user_id = :userId AND c.is_public = TRUE " +
                                        "GROUP BY w.id, c.id, c.title, c.thumbnail_url, c.level, " +
                                        "c.author_name, c.price, c.discount_price"
                        )
                        .bind("userId", userId)
                        .map((rs, ctx) -> {
                            Course course = new Course();
                            course.setWishlistId(rs.getInt("wishlistId"));
                            course.setId(rs.getInt("id"));
                            course.setTitle(rs.getString("title"));
                            course.setThumbnailUrl(rs.getString("thumbnailUrl"));
                            course.setLevel(rs.getString("level"));
                            course.setAuthorName(rs.getString("authorName"));
                            course.setPrice(rs.getInt("price"));
                            course.setDiscountPrice(rs.getInt("discountPrice"));
                            course.setDurationHours(rs.getDouble("durationHours"));
                            return course;
                        })
                        .list()
        );
    }


}