package vn.edu.nlu.fit.elearning.feature.enrollment.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;

import java.util.List;

public class EnrollmentDaoImpl extends BaseDao implements EnrollmentDao {

//    public List<EnrollmentCardDTO> findAll() {
//        return getJdbi().withHandle(handle -> {
//            return handle.createQuery("SELECT c.id AS course_id, c.title, c.author_name, \n" +
//                    "       ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed\n" +
//                    "FROM enrollments e LEFT JOIN courses c ON e.course_id = c.id \n" +
//                    "    LEFT JOIN lessons l ON l.course_id = c.id\n" +
//                    "    LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id\n" +
//                    "WHERE e.user_id = 7\n" +
//                    "GROUP BY c.id, c.title").mapToBean(EnrollmentCardDTO.class).list();
//        });
//    }

    @Override
    public List<EnrollmentCardDTO> findAllCoursesCard(int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id AS course_id, c.title, c.author_name, c.thumbnail_url , " +
                            "ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed, " +
                            "COALESCE(AVG(r.rating)) AS rating " +
                            "FROM enrollments e " +
                            "LEFT JOIN courses c ON e.course_id = c.id " +
                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                            "LEFT JOIN reviews r ON r.course_id = c.id " +
                            "LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id " +
                            "WHERE e.user_id = :userId " +
                            "GROUP BY c.id, c.title")
                    .bind("userId", userId)
                    .mapToBean(EnrollmentCardDTO.class)
                    .list();
        });
    }

    @Override
    public int createEnrollment(Enrollment entity) {
        String sql = "INSERT INTO enrollments (user_id, course_id, order_id) " +
                "VALUES (:userId, :courseId, :orderId)";
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(entity)
                        .execute()
        );
    }

    @Override
    public EnrollmentDetailDto findEnrollmentDetail(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT e.id AS id, c.id AS courseId, c.description, c.title AS title, c.author_name AS authorName, " +
                            "    (SELECT IFNULL(AVG(r.rating), 0) FROM reviews r WHERE r.course_id = c.id) AS rating, " +
                            "    (SELECT IFNULL(SUM(l.duration_minutes), 0) / 60 FROM lessons l WHERE l.course_id = c.id) AS durationHours, " +
                            "    (SELECT COUNT(*) FROM enrollments e2 WHERE e2.course_id = c.id) AS studentCount, " +
                            "    (SELECT COUNT(*) FROM reviews r WHERE r.course_id = c.id) AS reviewCount, " +
                            "    ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed " +
                            "FROM enrollments e " +
                            "JOIN courses c ON e.course_id = c.id " +
                            "LEFT JOIN lessons l ON l.course_id = c.id " +
                            "LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id " +
                            "WHERE e.user_id = :userId AND e.course_id = :courseId")
                    .bind("userId", userId)
                    .bind("courseId", courseId)
                    .mapToBean(EnrollmentDetailDto.class)
                    .findFirst().orElse(null);
        });
    }

    @Override
    public int findNewPercentComplete(int enrollmentId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) " +
                            "FROM enrollments e " +
                            "LEFT JOIN lessons l ON l.course_id = e.course_id " +
                            "LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id " +
                            "WHERE e.id = :enrollmentId " +
                            "GROUP BY e.id")
                    .bind("enrollmentId", enrollmentId)
                    .mapTo(Integer.class)
                    .findFirst()
                    .orElse(0);
        });
    }

    @Override
    public int findCourseIdById(int enrollmentId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT e.course_id FROM enrollments e WHERE e.id = :enrollmentId")
                    .bind("enrollmentId", enrollmentId)
                    .mapTo(Integer.class)
                    .one();
        });
    }

}