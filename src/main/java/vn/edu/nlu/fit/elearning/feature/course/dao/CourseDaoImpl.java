package vn.edu.nlu.fit.elearning.feature.course.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.common.utils.search.CourseFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDaoImpl extends BaseDao implements CourseDao {


    @Override
    public int create(Course entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO courses( id, title, subtitle, level, goals, description, price, discount_price, thumbnail_url, is_public, category_id, author_name)\n" +
                            "VALUES (:id, :title,  :subtitle,  :level,  :goals ,  :description, :price, :discountPrice, :thumbnailUrl, :isPublic, :categoryId, :authorName)")
                    .bindBean(entity)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public Course findById(Integer integer) {
        return getJdbi().withHandle(handle -> {
                    return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.goals, c.level, c.price, c.discount_price, c.thumbnail_url, c.is_public, c.category_id, c.author_name, c.created_at, c.updated_at\n" +
                                    "FROM courses c\n" +
                                    "WHERE c.id = :id ")
                            .bind("id", integer)
                            .mapToBean(Course.class)
                            .findFirst()
                            .orElse(null);
                }
        );
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public int update(Course entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE courses\n" +
                            "SET\n" +
                            "    title = :title,\n" +
                            "    subtitle = :subtitle,\n" +
                            "    level = :level,\n" +
                            "    goals = :goals,\n" +
                            "    description = :description,\n" +
                            "    price = :price,\n" +
                            "    discount_price = :discountPrice,\n" +
                            "    thumbnail_url = :thumbnailUrl,\n" +
                            "    is_public = :isPublic,\n" +
                            "    category_id = :categoryId\n" +
                            "WHERE id = :id")
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public int delete(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM courses WHERE id = :id")
                    .bind("id", integer)
                    .execute();
        });
    }

    @Override
    public List<Course> findAllCourses() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, " +
                    "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, " +
                    "c.author_name, c.discount_price, c.price, c.created_at, c.is_public\n" +
                    "FROM courses c\n" +
                    "LEFT JOIN lessons l ON c.id = l.course_id\n" +
                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id\n" +
                    "ORDER BY c.id DESC;").mapToBean(Course.class).list();
        });
    }

    @Override
    public CourseDetailDto findCourseByIdForDetail(int id, int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.goals, c.level, c.price, c.discount_price, \n" +
                            "c.thumbnail_url, c.is_public, c.author_name, c.created_at, c.updated_at, cat.id AS categoryId, \n" +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId > 0 AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, \n" +
                            "(SELECT COUNT(*) FROM lessons WHERE course_id = c.id) AS lessonCount,\n" +
                            "COALESCE(AVG(r.rating)) AS avgRating, " +
                            "COUNT(DISTINCT r.id) AS reviewCount\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN categories cat ON c.category_id = cat.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE AND c.id = :id\n" +
                            "GROUP BY c.id, cat.id")
                    .bind("id", id)
                    .bind("userId", userId)
                    .mapToBean(CourseDetailDto.class)
                    .findFirst()
                    .orElse(null);

        });
    }

    @Override
    public CourseCardDto findCourseCardById(int id, int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.level, c.price, c.discount_price, " +
                            "c.thumbnail_url, c.is_public, c.author_name, " +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, \n" +
                            "COALESCE(AVG(r.rating)) AS avgRating, " +
                            "COUNT(DISTINCT r.id) AS reviewCount\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE AND c.id = :id " +
                            "GROUP BY c.id")
                    .bind("id", id)
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public List<Course> filterAllCourses(CourseFilter filter, int limit, int offset) {
        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.level, c.price, c.is_public, c.created_at, " +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount,\n" +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS duration_hours \n" +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "WHERE 1=1"
            );

            Map<String, Object> params = new HashMap<>();

            //(Public hay All)
            if (filter.getIsPublic() != null) {
                sql.append(" AND c.is_public = :isPublic");
                params.put("isPublic", filter.getIsPublic());
            }

            // Danh mục
            if (filter.getCategoryId() != null) {
                sql.append(" AND c.category_id = :catId");
                params.put("catId", filter.getCategoryId());
            }

            // Tìm kiếm theo tên
            if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
                String processedTitle = filter.getTitle().trim()
                        .replace("!", "!!")   // Thoát chính ký tự thoát trước
                        .replace("%", "!%")   // Biến % thành !%
                        .replace("_", "!_");  // Biến _ thành !_

                sql.append(" AND c.title LIKE :title");
                params.put("title", "%" + processedTitle + "%");
            }

            // Khoảng giá (Sử dụng cột tính toán giá sau giảm)
            if ("under500".equals(filter.getPriceRange())) {
                sql.append(" AND (c.price - COALESCE(c.discount_price, 0)) < 500000");
            }

            // Kiếm theo cấp độ
            if (filter.getLevel() != null && !filter.getLevel().isEmpty()) {
                sql.append(" AND c.level = :level");
                params.put("level", filter.getLevel());
            }

            if (filter.getCreatedAt() != null && !filter.getCreatedAt().isEmpty()) {
                sql.append(" AND c.created_at >= :dateFrom");
                params.put("dateFrom", filter.getCreatedAt());
            }

            sql.append(" GROUP BY c.id");

            sql.append(" ORDER BY c.id DESC");

            // Thời lượng (Sử dụng HAVING vì duration_hours là hàm tổng hợp)
            if (filter.getDuration() != null && !filter.getDuration().isEmpty()) {
                if ("short".equals(filter.getDuration())) {
                    sql.append(" HAVING duration_hours < 5");
                }
            }

            sql.append(" LIMIT :limit OFFSET :offset");

            var query = handle.createQuery(sql.toString());
            params.forEach(query::bind);
            query.bind("limit", limit);
            query.bind("offset", offset);

            return query.mapToBean(Course.class).list();
        });
    }

    @Override
    public int countAdminAllCourses(CourseFilter filter) {
        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.level, c.price, c.is_public, c.created_at, " +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount,\n" +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS duration_hours \n" +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "WHERE 1=1"
            );

            Map<String, Object> params = new HashMap<>();

            //(Public hay All)
            if (filter.getIsPublic() != null) {
                sql.append(" AND c.is_public = :isPublic");
                params.put("isPublic", filter.getIsPublic());
            }

            // Danh mục
            if (filter.getCategoryId() != null) {
                sql.append(" AND c.category_id = :catId");
                params.put("catId", filter.getCategoryId());
            }

            // Tìm kiếm theo tên
            if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
                String processedTitle = filter.getTitle().trim()
                        .replace("!", "!!")   // Thoát chính ký tự thoát trước
                        .replace("%", "!%")   // Biến % thành !%
                        .replace("_", "!_");  // Biến _ thành !_

                sql.append(" AND c.title LIKE :title");
                params.put("title", "%" + processedTitle + "%");
            }

            // Khoảng giá (Sử dụng cột tính toán giá sau giảm)
            if ("under500".equals(filter.getPriceRange())) {
                sql.append(" AND (c.price - COALESCE(c.discount_price, 0)) < 500000");
            }

            // Kiếm theo cấp độ
            if (filter.getLevel() != null && !filter.getLevel().isEmpty()) {
                sql.append(" AND c.level = :level");
                params.put("level", filter.getLevel());
            }

            if (filter.getCreatedAt() != null && !filter.getCreatedAt().isEmpty()) {
                sql.append(" AND c.created_at >= :dateFrom");
                params.put("dateFrom", filter.getCreatedAt());
            }

            sql.append(" GROUP BY c.id");

            sql.append(" ORDER BY c.id DESC");

            // Thời lượng (Sử dụng HAVING vì duration_hours là hàm tổng hợp)
            if (filter.getDuration() != null && !filter.getDuration().isEmpty()) {
                if ("short".equals(filter.getDuration())) {
                    sql.append(" HAVING duration_hours < 5");
                }
            }

            String finalSql = "SELECT COUNT(*) FROM (" + sql.toString() + ") AS total_count";

            var query = handle.createQuery(finalSql);
            params.forEach(query::bind);

            return query.mapTo(Integer.class).one();
        });
    }


    @Override
    public List<CourseCardDto> findCourseSuggestByTitle(String keyword) {

        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.price, c.discount_price\n" +
                            "FROM courses c\n" +
                            "WHERE c.is_public = TRUE\n" +
                            "AND c.title LIKE :keyword\n" +
                            "ORDER BY c.title\n" +
                            "LIMIT 5")
                    .bind("keyword", "%" + keyword + "%")
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }

}
