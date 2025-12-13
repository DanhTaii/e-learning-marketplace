package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;

import java.util.List;

public class CourseDao extends BaseDao implements BaseCrudDao<Course, Integer> {


    @Override
    public void create(Course entity) {

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

    public List<Course> getAllCourses() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.student_count, SUM(l.duration_minutes) / 60.0 AS duration_hours, c.author_name, (c.price - c.discount_price) AS price_new, c.price AS price_old, c.rating, c.created_at, c.is_public\n" +
                    "FROM Courses c\n" +
                    "LEFT JOIN Lessons l ON c.id = l.course_id\n" +
//                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id, c.level\n" +
                    "ORDER BY c.id DESC;").mapToBean(Course.class).list();
        });
    }

    // 3 khóa học được yêu thích nhiều nhất
    public List<Course> getThreeCoursesWereLiked() {
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
    public List<Course> getSixCoursesMostPopular() {
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
    public List<Course> getSixCoursesLast() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.is_featured ,c.thumbnail_url,c.level,c.student_count,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name,(c.price - c.discount_price) AS price_new,c.price AS price_old,c.rating\n" +
                    "FROM Courses c\n" +
                    "LEFT JOIN Lessons l ON l.course_id = c.id\n" +
                    "WHERE c.is_featured = TRUE AND c.is_public = TRUE\n" +
                    "GROUP BY c.id, c.level\n" +
                    "ORDER BY c.created_at DESC\n" +
                    "LIMIT 6").mapToBean(Course.class).list();
        });
    }

    public List<Course> getAllCoursesAdmin() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.thumbnail_url, c.level, c.student_count, SUM(l.duration_minutes) / 60.0 AS duration_hours, c.author_name, (c.price - c.discount_price) AS price_new, c.price AS price_old, c.rating, c.created_at, c.is_public\n" +
                    "FROM Courses c\n" +
                    "LEFT JOIN Lessons l ON c.id = l.course_id\n" +
                    "GROUP BY c.id, c.level\n" +
                    "ORDER BY c.id DESC;").mapToBean(Course.class).list();
        });
    }

    public Course getCourse(int id) {
        Course course = getJdbi().withHandle(handle ->
                handle.createQuery("SELECT c.id, c.title, c.description, c.goals, c.price, c.discount_price, (c.price - c.discount_price) AS price_new, c.rating, c.student_count, c.thumbnail_url, c.author_name, c.updated_at, cat.name AS category_name, cat.slug AS category_slug, parent.name AS parent_category_name, parent.slug AS parent_category_slug, COUNT(DISTINCT l.id) AS lesson_count, IFNULL(SUM(l.duration_minutes),0) AS total_duration_minutes " +
                                "FROM Courses c " +
                                "LEFT JOIN Categories cat ON c.category_id = cat.id " +
                                "LEFT JOIN Categories parent ON cat.parent_id = parent.id " +
                                "LEFT JOIN Lessons l ON c.id = l.course_id " +
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

        return course;
    }

    public List<Course> getCoursesByIdCategory(int idCategory) {
        return getJdbi().withHandle(handle ->{
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.price, c.discount_price, c.rating, c.student_count, c.thumbnail_url, cate.id AS category_id, cate.name AS category_name " +
                    "FROM Courses c " +
                    "JOIN Categories cate ON c.category_id = cate.id " +
                    "WHERE cate.id = :id;").bind("id", idCategory).mapToBean(Course.class).list();
        });
    }

}
