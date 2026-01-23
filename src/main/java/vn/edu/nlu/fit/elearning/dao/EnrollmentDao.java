package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.model.Enrollment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnrollmentDao extends BaseDao implements BaseCrudDao<EnrollmentCardDTO, Integer> {

    @Override
    public int create(EnrollmentCardDTO entity) {
        return 0;
    }

    @Override
    public EnrollmentCardDTO findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<EnrollmentCardDTO> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id AS course_id, c.title, c.author_name, \n" +
                    "       ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed\n" +
                    "FROM enrollments e LEFT JOIN courses c ON e.course_id = c.id \n" +
                    "    LEFT JOIN lessons l ON l.course_id = c.id\n" +
                    "    LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id\n" +
                    "WHERE e.user_id = 7\n" +
                    "GROUP BY c.id, c.title").mapToBean(EnrollmentCardDTO.class).list();
        });
    }

    @Override
    public int update(EnrollmentCardDTO entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }

    public List<EnrollmentCardDTO> findAllCoursesCard(int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id AS course_id, c.title, c.author_name, c.thumbnail_url ,\n" +
                            "       ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed\n" +
                            "FROM enrollments e LEFT JOIN courses c ON e.course_id = c.id \n" +
                            "    LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "    LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id\n" +
                            "WHERE e.user_id = :userId\n" +
                            "GROUP BY c.id, c.title")
                    .bind("userId", userId)
                    .mapToBean(EnrollmentCardDTO.class)
                    .list();
        });
    }

    public int createEnrollment(Enrollment entity) {
        String sql = "INSERT INTO enrollments (user_id, course_id, order_id) " +
                "VALUES (:userId, :courseId, :orderId)";
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(entity)
                        .execute()
        );
    }

    public EnrollmentDetailDto getEnrollmentDetail(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT e.id AS id, c.id AS courseId, c.title AS title, c.author_name AS authorName,\n" +
                            "    (SELECT IFNULL(AVG(r.rating), 0) FROM reviews r WHERE r.course_id = c.id) AS rating,\n" +
                            "    (SELECT IFNULL(SUM(l.duration_minutes), 0) / 60 FROM lessons l WHERE l.course_id = c.id) AS durationHours,\n" +
                            "    (SELECT COUNT(*) FROM enrollments e2 WHERE e2.course_id = c.id) AS studentCount,\n" +
                            "    (SELECT COUNT(*) FROM reviews r WHERE r.course_id = c.id) AS reviewCount\n" +
                            "FROM enrollments e\n" +
                            "JOIN courses c ON e.course_id = c.id\n" +
                            "WHERE e.user_id = :userId AND e.course_id = :courseId")
                    .bind("userId", userId)
                    .bind("courseId", courseId)
                    .mapToBean(EnrollmentDetailDto.class)
                    .findFirst().orElse(null);
        });
    }
}