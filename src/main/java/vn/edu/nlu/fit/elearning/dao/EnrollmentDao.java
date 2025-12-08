package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Enrollment;

import java.util.List;

public class EnrollmentDao extends BaseDao implements BaseCrudDao<Enrollment, Integer> {

    @Override
    public void create(Enrollment entity) {
        // TODO: Implement create logic
    }

    @Override
    public Enrollment findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<Enrollment> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT e.id AS enrollment_id, e.user_id, u.first_name, u.last_name, e.course_id, c.title AS course_title, e.order_id, e.enrolled_at\n" +
                    "FROM Enrollments e\n" +
                    "JOIN Users u ON e.user_id = u.id\n" +
                    "JOIN Courses c ON e.course_id = c.id\n" +
                    "ORDER BY e.enrolled_at DESC;\n").mapToBean(Enrollment.class).list();
        });
    }

    @Override
    public int update(Enrollment entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }


}