package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.Review;
import vn.edu.nlu.fit.elearning.utils.CourseFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDao extends BaseDao implements BaseCrudDao<Course, Integer> {


    @Override
    public int create(Course entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO Courses( id, title, subtitle, level, goals, description, price, discount_price, thumbnail_url)\n" +
                            "VALUES (:id, :title,  :subtitle,  :level,  :goals ,  :description, :price, :discountPrice, :thumbnailUrl)")
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public Course findById(Integer integer) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public int update(Course entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    public List<Course> findAllCourses() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.student_count, SUM(l.duration_minutes) / 60.0 AS duration_hours, c.author_name, (c.price - c.discount_price) AS price_new, c.price AS price_old, c.rating, c.created_at, c.is_public\n" +
                    "FROM Courses c\n" +
                    "LEFT JOIN Lessons l ON c.id = l.course_id\n" +
                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id, c.level\n" +
                    "ORDER BY c.id DESC;").mapToBean(Course.class).list();
        });
    }

    // 3 khóa học được yêu thích nhiều nhất
    public List<Course> findThreeCoursesWereLiked() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.thumbnail_url, c.level,c.student_count,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name,(c.price - c.discount_price) AS price_new,\n" +
                    "c.price AS price_old,c.rating,COUNT(w.course_id) AS wishlist_count\n" +
                    "FROM Wishlist w JOIN Courses c ON w.course_id = c.id\n" +
                    "LEFT JOIN Lessons l ON l.course_id = c.id\n" +
                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id, c.title, c.thumbnail_url, c.level, c.student_count, c.author_name, c.price, c.discount_price, c.rating\n" +
                    "ORDER BY wishlist_count DESC\n" +
                    "LIMIT 3;").mapToBean(Course.class).list();
        });
    }

    // 6 khóa học mới nhất
    public List<Course> findSixCoursesLast() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title,c.thumbnail_url,c.level,c.student_count,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name,(c.price - c.discount_price) AS price_new,\n" +
                    "c.price AS price_old,c.rating\n" +
                    "FROM Courses c\n" +
                    "LEFT JOIN Lessons l ON l.course_id = c.id\n" +
                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id, c.level\n" +
                    "ORDER BY c.created_at DESC\n" +
                    "LIMIT 6;").mapToBean(Course.class).list();
        });
    }

    // 6 khóa học phổ biến nhất
    public List<Course> findSixCoursesMostPopular() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.thumbnail_url, c.level, c.student_count, c.rating, c.price, c.discount_price, (c.price - c.discount_price) AS price_new, c.author_name, COALESCE(SUM(l.duration_minutes), 0) / 60.0 AS duration_hours " +
                    "FROM Courses c " +
                    "LEFT JOIN Lessons l ON l.course_id = c.id " +
                    "WHERE c.is_public = TRUE " +
                    "GROUP BY c.id " +
                    "ORDER BY c.student_count DESC, c.rating DESC " +
                    "LIMIT 6;").mapToBean(Course.class).list();
        });
    }

    // 1 khóa học phổ biến nhất
    public Course findCoursesMostPopular() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.thumbnail_url, c.level, c.student_count, c.rating, c.price, c.discount_price, (c.price - c.discount_price) AS price_new, c.author_name, COALESCE(SUM(l.duration_minutes), 0) / 60.0 AS duration_hours " +
                    "FROM Courses c " +
                    "LEFT JOIN Lessons l ON l.course_id = c.id " +
                    "WHERE c.is_public = TRUE " +
                    "GROUP BY c.id " +
                    "ORDER BY c.student_count DESC, c.rating DESC " +
                    "LIMIT 1;").mapToBean(Course.class).one();
        });
    }

    public List<CourseCardDto> findAllCoursesCard(){
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.author_name, c.price, w.user_id,(c.price - c.discount_price) AS price_new,c.thumbnail_url, c.level, COALESCE(AVG(r.rating), rating) AS avgRating, COALESCE(SUM(l.duration_minutes),0) / 60.0 AS durationHours\n" +
                    "FROM Courses c\n" +
                    "LEFT JOIN Lessons l ON l.course_id = c.id\n" +
                    "LEFT JOIN Reviews r ON r.course_id = c.id\n" +
                    "LEFT JOIN Wishlist w ON w.course_id = c.id\n" +
                    "WHERE c.is_public = TRUE AND c.id = 1\n" +
                    "GROUP BY c.id").mapToBean(CourseCardDto.class).list();
        });
    }

    public Course findCourseByIdForDetail(int id) {
        Course course = getJdbi().withHandle(handle ->
                handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.goals, c.level, c.price, (c.price - c.discount_price) AS price_new, c.rating, c.thumbnail_url, c.is_public, c.author_name, c.created_at, c.updated_at, cat.name AS categoryName, parent.name AS parentCategoryName, COALESCE(SUM(l.duration_minutes),0) / 60.0 AS durationHours, COUNT(l.id) AS lessonCount, COALESCE(AVG(r.rating), c.rating) AS avgRating, COUNT(DISTINCT r.id) AS reviewCount\n" +
                        "FROM Courses c\n" +
                        "LEFT JOIN Categories cat ON c.category_id = cat.id\n" +
                        "LEFT JOIN Categories parent ON cat.parent_id = parent.id\n" +
                        "LEFT JOIN Lessons l ON l.course_id = c.id\n" +
                        "LEFT JOIN Reviews r ON r.course_id = c.id\n" +
                        "WHERE c.is_public = TRUE AND c.id = :id " +
                        "GROUP BY c.id, cat.id, parent.id").bind("id", id).mapToBean(Course.class).one()
        );
        // lấy tags riêng
        List<String> tags = getJdbi().withHandle(handle ->
                handle.createQuery("SELECT t.name FROM Tags t " +
                        "JOIN Course_Tags ct ON t.id = ct.tag_id " +
                        "WHERE ct.course_id = :id").bind("id", id).mapTo(String.class).list()
        );
        course.setTags(tags); // thêm vào đối tượng Course
        //
        List<Lesson> lessons = getJdbi().withHandle(handle -> handle.createQuery("SELECT id, title, video_url, duration_minutes, order_index " +
                "FROM Lessons WHERE course_id = :id ORDER BY order_index").bind("id", id).mapToBean(Lesson.class).list());
        course.setLessons(lessons);
        //
        List<Review> reviews = getJdbi().withHandle(handle -> handle.createQuery("SELECT r.id, r.rating, r.comment, r.created_at, " +
                "u.first_name, u.last_name, u.avatar_url " +
                "FROM Reviews r JOIN Users u ON r.user_id = u.id " +
                "WHERE r.course_id = :id ORDER BY r.created_at DESC").bind("id", id).mapToBean(Review.class).list());
        course.setReviews(reviews);

        return course;
    }

    public List<Course> findCoursesByIdCategory(int idCategory) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.price, c.discount_price, c.rating, c.student_count, c.thumbnail_url, cate.id AS category_id, cate.name AS category_name " +
                    "FROM Courses c " +
                    "JOIN Categories cate ON c.category_id = cate.id " +
                    "WHERE cate.id = :id;").bind("id", idCategory).mapToBean(Course.class).list();
        });
    }

    public List<Course> findCoursesByTitle(String search) {
        String title = "%" + search + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.price, c.discount_price, c.rating, c.student_count, c.thumbnail_url, cate.id AS category_id, cate.name AS category_name \n" +
                    "FROM Courses c\n" +
                    "JOIN Categories cate ON c.category_id = cate.id\n" +
                    "WHERE title LIKE :title").bind("title", title).mapToBean(Course.class).list();
        });
    }

    // làm cho phần bộ lọc
    // làm cách này thì tích 1 hay nhiều cái thì vẫn đều lọc bình thường
    public List<Course> filterCourses(Integer categoryId, String title,
                                      String sortPrice, String level,
                                      String priceRange, String rating,
                                      String duration, String popular) {
        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.subtitle, c.level, c.price, c.discount_price, " +
                            "c.rating, c.student_count, c.thumbnail_url, cate.id AS category_id, cate.name AS category_name, " +
                            "SUM(l.duration_minutes)/60.0 AS duration_hours " +
                            "FROM Courses c " +
                            "JOIN Categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN Lessons l ON c.id = l.course_id " +
                            "WHERE c.is_public = TRUE "
            );
            // lọc theo category
            if (categoryId != null) {
                sql.append(" AND cate.id = :idCategory");
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
            // lọc theo rating
            if ("low".equals(rating)) {
                sql.append(" AND c.rating < 3");
            } else if ("high".equals(rating)) {
                sql.append(" AND c.rating >= 3");
            }
            // lọc theo phổ biến
            if ("true".equals(popular)) {
                sql.append(" AND c.is_featured = TRUE");
            }
            // group by để tính duration_hours
            sql.append(" GROUP BY c.id, cate.id");
            // lọc theo duration (HAVING phải sau GROUP BY)
            if ("short".equals(duration)) {
                sql.append(" HAVING duration_hours < 5");
            } else if ("medium".equals(duration)) {
                sql.append(" HAVING duration_hours BETWEEN 5 AND 10");
            } else if ("long".equals(duration)) {
                sql.append(" HAVING duration_hours > 10");
            }
            // sort theo giá
            if ("asc".equals(sortPrice)) {
                sql.append(" ORDER BY (c.price - c.discount_price) ASC");
            } else if ("desc".equals(sortPrice)) {
                sql.append(" ORDER BY (c.price - c.discount_price) DESC");
            }
            var query = handle.createQuery(sql.toString());
            if (categoryId != null) query.bind("idCategory", categoryId);
            if (title != null && !title.isEmpty()) query.bind("title", "%" + title + "%");
            if (level != null) query.bind("level", level);
            return query.mapToBean(Course.class).list();
        });
    }


    public List<Course> filterAllCourses(CourseFilter filter) {
        return getJdbi().withHandle(handle -> {
            StringBuilder sql = new StringBuilder(
                    "SELECT c.id, c.title, c.subtitle, c.level, c.price, c.discount_price, c.is_public, c.created_at, " +
                            "c.rating, c.student_count, c.thumbnail_url, cate.id AS category_id, cate.name AS category_name, " +
                            "SUM(l.duration_minutes)/60.0 AS duration_hours " +
                            "FROM Courses c " +
                            "LEFT JOIN Categories cate ON c.category_id = cate.id " +
                            "LEFT JOIN Lessons l ON c.id = l.course_id " +
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

//            // Sắp xếp
//            if ("desc".equals(filter.getSortPrice())) {
//                sql.append(" ORDER BY (c.price - c.discount_price) DESC");
//            }

            var query = handle.createQuery(sql.toString());
            params.forEach(query::bind);

            return query.mapToBean(Course.class).list();
        });
    }

}
