package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.EnrollmentDTO;
import vn.edu.nlu.fit.elearning.model.Enrollment;

import java.util.List;

public class EnrollmentDao extends BaseDao implements BaseCrudDao<EnrollmentDTO, Integer> {

    @Override
    public int create(EnrollmentDTO entity) {
        // TODO: Implement create logic
        return 0;
    }

    @Override
    public EnrollmentDTO findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<EnrollmentDTO> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id AS course_id, c.title, c.author_name, c.rating,\n" +
                    "       ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed\n" +
                    "FROM enrollments e LEFT JOIN courses c ON e.course_id = c.id \n" +
                    "    LEFT JOIN lessons l ON l.course_id = c.id\n" +
                    "    LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id\n" +
                    "WHERE e.user_id = 7\n" +
                    "GROUP BY c.id, c.title").mapToBean(EnrollmentDTO.class).list();
        });
    }

    @Override
    public int update(EnrollmentDTO entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }

    public List<EnrollmentDTO> findAllCoursesCard(int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id AS course_id, c.title, c.author_name, c.thumbnail_url , c.rating,\n" +
                            "       ROUND(IFNULL(SUM(CASE WHEN ulp.is_completed = 1 THEN 1 ELSE 0 END) / COUNT(l.id) * 100, 0), 2) AS percent_completed\n" +
                            "FROM enrollments e LEFT JOIN courses c ON e.course_id = c.id \n" +
                            "    LEFT JOIN lessons l ON l.course_id = c.id\n" +
                            "    LEFT JOIN user_lesson_progress ulp ON ulp.lesson_id = l.id AND ulp.user_id = e.user_id\n" +
                            "WHERE e.user_id = :userId\n" +
                            "GROUP BY c.id, c.title")
                    .bind("userId", userId)
                    .mapToBean(EnrollmentDTO.class)
                    .list();
        });
    }

}