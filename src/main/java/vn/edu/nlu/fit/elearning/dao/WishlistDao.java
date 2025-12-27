package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Wishlist;

import java.util.List;

public class WishlistDao extends BaseDao implements BaseCrudDao<Wishlist, Integer> {

    @Override
    public int create(Wishlist entity) {
        // TODO: Implement create logic
        return 0;
    }

    @Override
    public Wishlist findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<Wishlist> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.is_featured ,c.thumbnail_url,c.level,c.student_count,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name,(c.price - c.discount_price) AS price_new,c.price AS price_old,c.rating\n" +
                    "FROM users u JOIN wishlist w ON u.id = w.user_id\n" +
                    "  JOIN courses c ON w.course_id = c.id\n" +
                    "  LEFT JOIN lessons l ON l.course_id = c.id\n" +
                    "WHERE u.id = :id AND c.is_public = TRUE").mapToBean(Wishlist.class).list();
        });
    }

    @Override
    public int update(Wishlist entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return getJdbi().withHandle(handle ->{
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
                    handle.createQuery(
                                    "SELECT w.id AS wishlistId, " +
                                            "c.id, " +
                                            "c.title, " +
                                            "c.thumbnail_url AS thumbnailUrl, " +
                                            "c.level, " +
                                            "c.student_count AS studentCount, " +
                                            "c.author_name AS authorName, " +
                                            "c.price, " +
                                            "c.discount_price AS discountPrice, " +
                                            "c.rating, " +
                                            "COALESCE(SUM(l.duration_minutes), 0) / 60.0 AS durationHours " +
                                            "FROM Wishlist w " +
                                            "JOIN Courses c ON w.course_id = c.id " +
                                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                                            "WHERE w.user_id = :userId AND c.is_public = TRUE " +
                                            "GROUP BY w.id, c.id, c.title, c.thumbnail_url, c.level, c.student_count, " +
                                            "c.author_name, c.price, c.discount_price, c.rating"
                            )
                            .bind("userId", userId)
                            .map((rs, ctx) -> {
                                Course course = new Course();
                                course.setWishlistId(rs.getInt("wishlistId"));
                                course.setId(rs.getInt("id"));
                                course.setTitle(rs.getString("title"));
                                course.setThumbnailUrl(rs.getString("thumbnailUrl"));
                                course.setLevel(rs.getString("level"));
                                course.setStudentCount(rs.getInt("studentCount"));
                                course.setAuthorName(rs.getString("authorName"));
                                course.setPrice(rs.getInt("price"));
                                course.setDiscountPrice(rs.getInt("discountPrice"));
                                course.setRating(rs.getDouble("rating"));
                                course.setDurationHours(rs.getDouble("durationHours"));
                                return course;
                            })
                            .list()
            );
        }


    public static void main(String[] args) {
        WishlistDao dao = new WishlistDao();
        System.out.println(dao.findAll().size());
    }
}