package vn.edu.nlu.fit.elearning.feature.index.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import java.util.List;

public class IndexDaoImpl extends BaseDao implements IndexDao {

    // 3 khóa học được yêu thích nhiều nhất
    @Override
    public List<CourseCardDto> findThreeCoursesWereLiked(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.thumbnail_url, c.level, " +
                            "c.author_name, c.price, c.discount_price, " +
                            "COUNT(DISTINCT w_total.user_id) AS wishlist_count,\n" +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS student_count,\n" +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId > 0 AND w_user.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as enrolled, \n" +
                            "COALESCE(AVG(r.rating)) AS avgRating " +
                            "FROM courses c \n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w_total ON w_total.course_id = c.id\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN wishlist w_user ON w_user.course_id = c.id AND w_user.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE\n" +
                            "GROUP BY c.id, w_user.user_id\n" +
                            "ORDER BY wishlist_count DESC\n" +
                            "LIMIT 3;")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }

    // 6 khóa học mới nhất
    @Override
    public List<CourseCardDto> findSixCoursesLast(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title,c.thumbnail_url,c.level,c.author_name, c.price, c.discount_price, " +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId > 0 AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, \n" +
                            "COALESCE(AVG(r.rating)) AS avgRating, " +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE\n" +
                            "GROUP BY c.id\n" +
                            "ORDER BY c.created_at DESC\n" +
                            "LIMIT 6;")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }

    // 6 khóa học phổ biến nhất
    @Override
    public List<CourseCardDto> findSixCoursesMostPopular(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.price, c.discount_price, c.author_name, " +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId > 0 AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, \n" +
                            "COALESCE(AVG(r.rating)) AS avgRating, " +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount\n" +
                            "FROM courses c " +
                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE " +
                            "GROUP BY c.id " +
                            "LIMIT 6;")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }

    // 1 khóa học phổ biến nhất
    @Override
    public CourseCardDto findCourseMostPopular(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.price, c.discount_price, " +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId > 0 AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, \n" +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount, " +
                            "COALESCE(AVG(r.rating)) AS avgRating " +
                            "FROM courses c " +
                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "WHERE c.is_public = TRUE " +
                            "GROUP BY c.id " +
                            "LIMIT 1;")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .findFirst()
                    .orElse(null);
        });
    }
}
