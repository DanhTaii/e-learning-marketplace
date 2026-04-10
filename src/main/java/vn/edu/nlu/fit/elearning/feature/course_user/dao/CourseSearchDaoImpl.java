package vn.edu.nlu.fit.elearning.feature.course_user.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.AllCourseFilter;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import java.util.ArrayList;
import java.util.List;

public class CourseSearchDaoImpl extends BaseDao implements CourseSearchDao {

    // làm cho phần bộ lọc
    // làm cách này thì tích 1 hay nhiều cái thì vẫn đều lọc bình thường
    @Override
    public List<CourseCardDto> filterResultSearchWithPagination(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level,
            String priceRange, String rating,
            String duration, String popular,
            int limit, int offset, int userId) {

        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.subtitle, c.level, c.price, c.discount_price, c.author_name, " +
                            "c.thumbnail_url, cate.id AS category_id, cate.name AS category_name, " +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount,\n" +
                            "COALESCE(AVG(r.rating), 0) AS avgRating, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "(CASE WHEN :userId IS NOT NULL AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, " +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours \n" +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN course_tags ct ON c.id = ct.course_id " +
                            "LEFT JOIN tags t ON ct.tag_id = t.id " +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN reviews r ON r.course_id = c.id " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE "
            );

            if (categoryId != null) {
                sql.append(" AND cate.id = :idCategory");
            }
            if (tagId != null) {
                sql.append(" AND t.id = :idTag");
            }
            // lọc theo title
            if (title != null && !title.isEmpty()) {
                sql.append(" AND c.title LIKE :title");
            }
            // lọc theo level
            if (level != null) {
                sql.append(" AND c.level = :level");
            }
            // lọc theo priceRange
            if ("under500".equals(priceRange)) {
                sql.append(" AND (c.price - c.discount_price) < 500000");
            } else if ("under1500".equals(priceRange)) {
                sql.append(" AND (c.price - c.discount_price) < 1500000");
            } else if ("over1500".equals(priceRange)) {
                sql.append(" AND (c.price - c.discount_price) >= 1500000");
            }

            sql.append(" GROUP BY c.id, cate.id");

            List<String> havingConditions = new ArrayList<>();

            if ("short".equals(duration)) {
                havingConditions.add("durationHours < 5");
            } else if ("medium".equals(duration)) {
                havingConditions.add("durationHours BETWEEN 5 AND 10");
            } else if ("long".equals(duration)) {
                havingConditions.add("durationHours > 10");
            }

            if ("low".equals(rating)) {
                havingConditions.add("COALESCE(AVG(r.rating), 0) < 3");
            } else if ("high".equals(rating)) {
                havingConditions.add("COALESCE(AVG(r.rating), 0) >= 3");
            }

            if (!havingConditions.isEmpty()) {
                sql.append(" HAVING " + String.join(" AND ", havingConditions));
            }

            if ("true".equals(popular)) {
                sql.append(" ORDER BY studentCount DESC"); // Ưu tiên sắp xếp theo độ phổ biến
            } else if ("asc".equals(sortPrice)) {
                sql.append(" ORDER BY (c.price - c.discount_price) ASC");
            } else if ("desc".equals(sortPrice)) {
                sql.append(" ORDER BY (c.price - c.discount_price) DESC");
            } else {
                sql.append(" ORDER BY c.id DESC"); // Mặc định là mới nhất (newest)
            }

            // Phân trang
            sql.append(" LIMIT :limit OFFSET :offset");

            var query = handle.createQuery(sql.toString());

            // Bind
            if (categoryId != null) query.bind("idCategory", categoryId);
            if (tagId != null) query.bind("idTag", tagId);

            if (title != null && !title.isEmpty()) {
                String processedTitle = title.trim()
                        .replace("!", "!!")   // Thoát chính ký tự thoát trước
                        .replace("%", "!%")   // Biến % thành !%
                        .replace("_", "!_");  // Biến _ thành !_

                query.bind("title", "%" + processedTitle + "%");
            }

            if (level != null) query.bind("level", level);

            query.bind("limit", limit);
            query.bind("offset", offset);
            query.bind("userId", userId);

            return query.mapToBean(CourseCardDto.class).list();
        });
    }

    @Override
    public List<CourseCardDto> filterAllCoursesWithPagination(AllCourseFilter allCourseFilter) {

        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.subtitle, c.level, c.price, c.discount_price, c.author_name, " +
                            "c.thumbnail_url, cate.id AS category_id, cate.name AS category_name, " +
                            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount,\n" +
                            "COALESCE(AVG(r.rating), 0) AS avgRating, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "(CASE WHEN :userId IS NOT NULL AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, " +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours \n" +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN course_tags ct ON c.id = ct.course_id " +
                            "LEFT JOIN tags t ON ct.tag_id = t.id " +
                            "LEFT JOIN reviews r ON r.course_id = c.id " +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE "
            );

            if (allCourseFilter.getCategoryId() != null) {
                sql.append(" AND cate.id = :idCategory");
            }

            sql.append(" GROUP BY c.id, cate.id ");

            if (allCourseFilter.isPopular()) {
                sql.append(" ORDER BY studentCount DESC "); // Ưu tiên sắp xếp theo độ phổ biến
            } else if ("asc".equals(allCourseFilter.getSortPrice())) {
                sql.append(" ORDER BY (c.price - c.discount_price) ASC ");
            } else if ("desc".equals(allCourseFilter.getSortPrice())) {
                sql.append(" ORDER BY (c.price - c.discount_price) DESC ");
            } else {
                sql.append(" ORDER BY c.id DESC "); // Mặc định là mới nhất (newest)
            }

            // Phân trang
            sql.append(" LIMIT :limit OFFSET :offset ");

            var query = handle.createQuery(sql.toString());

            // Bind
            if (allCourseFilter.getCategoryId() != null) query.bind("idCategory", allCourseFilter.getCategoryId());

            query.bind("limit", allCourseFilter.getLimit());
            query.bind("offset", allCourseFilter.getOffSet());
            query.bind("userId", allCourseFilter.getUserId());

            return query.mapToBean(CourseCardDto.class).list();
        });
    }

    // Đếm tổng số sau lọc
    @Override
    public int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level,
            String priceRange, String rating,
            String duration, String popular) {

        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT COUNT(*) FROM ( " +
                            "SELECT c.id " +
                            "FROM courses c " +
                            "JOIN categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN course_tags ct ON c.id = ct.course_id " +
                            "LEFT JOIN tags t ON ct.tag_id = t.id " +
                            "LEFT JOIN lessons l ON c.id = l.course_id " +
                            "LEFT JOIN reviews r ON r.course_id = c.id " +
                            "WHERE c.is_public = TRUE "
            );

            // Copy điều kiện lọc giống trên
            if (categoryId != null) {
                sql.append(" AND cate.id = :idCategory");
            }
            if (tagId != null) {
                sql.append(" AND t.id = :idTag");
            }
            if (title != null && !title.isEmpty()) {
                sql.append(" AND c.title LIKE :title");
            }
            if (level != null) {
                sql.append(" AND c.level = :level");
            }
            if ("under500".equals(priceRange)) {
                sql.append(" AND (c.price - c.discount_price) < 500000");
            } else if ("under1500".equals(priceRange)) {
                sql.append(" AND (c.price - c.discount_price) < 1500000");
            } else if ("over1500".equals(priceRange)) {
                sql.append(" AND (c.price - c.discount_price) >= 1500000");
            }

            sql.append(" GROUP BY c.id ");

            // HAVING cho duration và rating
            String having = "";
            if ("short".equals(duration) || "medium".equals(duration) || "long".equals(duration) ||
                    "low".equals(rating) || "high".equals(rating)) {
                having = "HAVING 1=1 ";
                if ("short".equals(duration))
                    having += " AND COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 < 5";
                if ("medium".equals(duration))
                    having += " AND COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 BETWEEN 5 AND 10";
                if ("long".equals(duration))
                    having += " AND COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 > 10";
                if ("low".equals(rating)) having += " AND COALESCE(AVG(r.rating), 0) < 3";
                if ("high".equals(rating)) having += " AND COALESCE(AVG(r.rating), 0) >= 3";
            }

            sql.append(having).append(") AS count_table");

            var query = handle.createQuery(sql.toString());

            // Bind giống trên
            if (categoryId != null) query.bind("idCategory", categoryId);
            if (tagId != null) query.bind("idTag", tagId);
            if (title != null && !title.isEmpty()) query.bind("title", "%" + title + "%");
            if (level != null) query.bind("level", level);

            return query.mapTo(Integer.class).one();
        });
    }

}
