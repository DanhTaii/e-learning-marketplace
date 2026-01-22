package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.Review;
import vn.edu.nlu.fit.elearning.utils.CourseFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDao extends BaseDao implements BaseCrudDao<Course, Integer> {


    @Override
    public int create(Course entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO courses( id, title, subtitle, level, goals, description, price, discount_price, thumbnail_url)\n" +
                            "VALUES (:id, :title,  :subtitle,  :level,  :goals ,  :description, :price, :discountPrice, :thumbnailUrl)")
                    .bindBean(entity)
                    .execute();
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

    public List<Course> findAllCourses() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, SUM(l.duration_minutes) / 60.0 AS duration_hours, c.author_name, c.discount_price, c.price, c.created_at, c.is_public\n" +
                    "FROM courses c\n" +
                    "LEFT JOIN lessons l ON c.id = l.course_id\n" +
                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id\n" +
                    "ORDER BY c.id DESC;").mapToBean(Course.class).list();
        });
    }

    // 3 khóa học được yêu thích nhiều nhất
    public List<CourseCardDto> findThreeCoursesWereLiked(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.thumbnail_url, c.level,SUM(l.duration_minutes) / 60.0 AS duration_hours," +
                            "c.author_name, c.price, c.discount_price, AVG(r.rating) AS avgRating,\n" +
                            "COUNT(DISTINCT w_total.user_id) AS wishlist_count,\n" +
                            "COUNT(DISTINCT e.id) AS student_count,\n" +
                            "(CASE WHEN :userId IS NOT NULL AND w_user.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist\n" +
                            "FROM courses c \n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w_total ON w_total.course_id = c.id\n" +
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
    public List<CourseCardDto> findSixCoursesLast(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title,c.thumbnail_url,c.level,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name, c.price, " +
                            "c.discount_price, AVG(r.rating) AS avgRating,\n" +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "COUNT(DISTINCT e.id) AS student_count\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
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
    public List<CourseCardDto> findSixCoursesMostPopular(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.price, c.discount_price, c.author_name, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "COALESCE(SUM(l.duration_minutes), 0) / 60.0 AS duration_hours, " +
                            "AVG(r.rating) as avgRating,\n" +
                            "COUNT(DISTINCT e.id) AS student_count\n" +
                            "FROM courses c " +
                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                            "LEFT JOIN enrollments e ON e.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE " +
                            "GROUP BY c.id " +
                            "LIMIT 6;")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }

    // các khóa học mới nhất
    public List<CourseCardDto> findCoursesLast(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title,c.thumbnail_url,c.level,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name, " +
                            "c.price, c.discount_price, \n" +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE\n" +
                            "GROUP BY c.id\n" +
                            "ORDER BY c.created_at DESC")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }


    // 1 khóa học phổ biến nhất
    public CourseCardDto findCourseMostPopular(Integer userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.price, c.discount_price, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "c.author_name, COALESCE(SUM(l.duration_minutes), 0) / 60.0 AS duration_hours\n" +
                            "FROM courses c " +
                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE " +
                            "GROUP BY c.id " +
                            "LIMIT 1;")
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    public CourseDetailDto findCourseByIdForDetail(int id, int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.goals, c.level, c.price, c.discount_price, " +
                            "c.thumbnail_url, c.is_public, c.author_name, c.created_at, c.updated_at, cat.name AS categoryName, " +
                            "parent.name AS parentCategoryName, COALESCE(SUM(l.duration_minutes),0) / 60.0 AS durationHours, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "COUNT(l.id) AS lessonCount, COALESCE(AVG(r.rating)) AS avgRating, COUNT(DISTINCT r.id) AS reviewCount\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN categories cat ON c.category_id = cat.id\n" +
                            "LEFT JOIN categories parent ON cat.parent_id = parent.id\n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE AND c.id = :id " +
                            "GROUP BY c.id, cat.id, parent.id")
                    .bind("id", id)
                    .bind("userId", userId)
                    .mapToBean(CourseDetailDto.class)
                    .findFirst()
                    .orElse(null);

        });
    }

    public CourseCardDto findCourseCardById(int id, int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.goals, c.level, c.price, c.discount_price, " +
                            "c.thumbnail_url, c.is_public, c.author_name, c.created_at, c.updated_at, cat.name AS categoryName, " +
                            "parent.name AS parentCategoryName, COALESCE(SUM(l.duration_minutes),0) / 60.0 AS durationHours, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "COUNT(l.id) AS lessonCount, COALESCE(AVG(r.rating)) AS avgRating, COUNT(DISTINCT r.id) AS reviewCount\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN categories cat ON c.category_id = cat.id\n" +
                            "LEFT JOIN categories parent ON cat.parent_id = parent.id\n" +
                            "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE AND c.id = :id " +
                            "GROUP BY c.id, cat.id, parent.id")
                    .bind("id", id)
                    .bind("userId", userId)
                    .mapToBean(CourseCardDto.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    // Lấy khóa học theo title (search)
    public List<CourseCardDto> findCoursesByTitle(String search) {
        String title = "%" + search + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(
                            "SELECT c.id, c.title, c.author_name, c.price, w.user_id, c.discount_price, c.thumbnail_url, c.level, \n" +
                                    "COALESCE(AVG(r.rating), 0) AS avgRating,\n" +
                                    "COUNT(DISTINCT e.id) AS student_count,\n" +
                                    "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                                    "COALESCE(SUM(l.duration_minutes),0) / 60.0 AS durationHours\n" +
                                    "FROM courses c\n" +
                                    "JOIN categories cate ON c.category_id = cate.id\n" +
                                    "LEFT JOIN lessons l ON l.course_id = c.id\n" +
                                    "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                                    "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                                    "WHERE c.is_public = TRUE AND c.title LIKE :title\n" +
                                    "GROUP BY c.id\n" +
                                    "ORDER BY c.created_at DESC"
                    )
                    .bind("title", title)
                    .mapToBean(CourseCardDto.class)
                    .list();
        });
    }


    // làm cho phần bộ lọc
    // làm cách này thì tích 1 hay nhiều cái thì vẫn đều lọc bình thường
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
                            "COUNT(DISTINCT e.id) AS studentCount, " +
                            "COALESCE(AVG(r.rating), 0) AS avgRating, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "COALESCE(SUM(l.duration_minutes), 0)/60.0 AS durationHours " +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN course_tags ct ON c.id = ct.course_id " +
                            "LEFT JOIN tags t ON ct.tag_id = t.id " +
                            "LEFT JOIN lessons l ON c.id = l.course_id " +
                            "LEFT JOIN enrollments e ON e.course_id = c.id " +
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
            if (title != null && !title.isEmpty()) query.bind("title", "%" + title + "%");
            if (level != null) query.bind("level", level);

            query.bind("limit", limit);
            query.bind("offset", offset);
            query.bind("userId", userId);

            return query.mapToBean(CourseCardDto.class).list();
        });
    }

    public List<CourseCardDto> filterAllCoursesWithPagination(
            Integer categoryId, String sortPrice, boolean popular,boolean newest,
            int limit, int offset, int userId) {

        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.subtitle, c.level, c.price, c.discount_price, c.author_name, " +
                            "c.thumbnail_url, cate.id AS category_id, cate.name AS category_name, " +
                            "COUNT(DISTINCT e.id) AS studentCount, " +
                            "COALESCE(AVG(r.rating), 0) AS avgRating, " +
                            "(CASE WHEN :userId IS NOT NULL AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist, " +
                            "COALESCE(SUM(l.duration_minutes), 0)/60.0 AS durationHours " +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN course_tags ct ON c.id = ct.course_id " +
                            "LEFT JOIN tags t ON ct.tag_id = t.id " +
                            "LEFT JOIN lessons l ON c.id = l.course_id " +
                            "LEFT JOIN reviews r ON r.course_id = c.id " +
                            "LEFT JOIN enrollments e ON e.course_id = c.id " +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE "
            );

            if (categoryId != null) {
                sql.append(" AND cate.id = :idCategory");
            }

            sql.append(" GROUP BY c.id, cate.id ");

            if (popular) {
                sql.append(" ORDER BY studentCount DESC "); // Ưu tiên sắp xếp theo độ phổ biến
            } else if ("asc".equals(sortPrice)) {
                sql.append(" ORDER BY (c.price - c.discount_price) ASC ");
            } else if ("desc".equals(sortPrice)) {
                sql.append(" ORDER BY (c.price - c.discount_price) DESC ");
            } else {
                sql.append(" ORDER BY c.id DESC "); // Mặc định là mới nhất (newest)
            }

            // Phân trang
            sql.append(" LIMIT :limit OFFSET :offset ");

            var query = handle.createQuery(sql.toString());

            // Bind
            if (categoryId != null) query.bind("idCategory", categoryId);

            query.bind("limit", limit);
            query.bind("offset", offset);
            query.bind("userId", userId);

            return query.mapToBean(CourseCardDto.class).list();
        });
    }

    // Đếm tổng số sau lọc
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
                if ("short".equals(duration)) having += " AND COALESCE(SUM(l.duration_minutes), 0)/60.0 < 5";
                if ("medium".equals(duration))
                    having += " AND COALESCE(SUM(l.duration_minutes), 0)/60.0 BETWEEN 5 AND 10";
                if ("long".equals(duration)) having += " AND COALESCE(SUM(l.duration_minutes), 0)/60.0 > 10";
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


    public List<Course> filterAllCourses(CourseFilter filter) {
        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.subtitle, c.level, c.price, c.discount_price, c.is_public, c.created_at, " +
                            "c.thumbnail_url, cate.id AS category_id, cate.name AS category_name, " +
                            "SUM(l.duration_minutes)/60.0 AS duration_hours " +
                            "FROM courses c " +
                            "LEFT JOIN categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN lessons l ON c.id = l.course_id " +
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
                sql.append(" AND c.title LIKE :title");
                params.put("title", "%" + filter.getTitle() + "%");
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

            // Thời lượng (Sử dụng HAVING vì duration_hours là hàm tổng hợp)
            if (filter.getDuration() != null && !filter.getDuration().isEmpty()) {
                if ("short".equals(filter.getDuration())) {
                    sql.append(" HAVING duration_hours < 5");
                }
            }


            var query = handle.createQuery(sql.toString());
            params.forEach(query::bind);

            return query.mapToBean(Course.class).list();
        });
    }

}
