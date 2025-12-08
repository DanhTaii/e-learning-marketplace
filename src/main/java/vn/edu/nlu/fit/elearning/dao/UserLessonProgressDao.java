package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.UserLessonProgress;

import java.util.List;

public class UserLessonProgressDao extends BaseDao implements BaseCrudDao<UserLessonProgress, Integer> {

    @Override
    public void create(UserLessonProgress entity) {
        // TODO: Implement create logic
    }

    @Override
    public UserLessonProgress findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<UserLessonProgress> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ulp.id AS progress_id, ulp.user_id, u.first_name, u.last_name, u.email, ulp.lesson_id, l.title AS lesson_title, l.course_id, ulp.is_completed, ulp.completed_at\n" +
                    "FROM user_lesson_progress ulp\n" +
                    "JOIN Users u ON ulp.user_id = u.id\n" +
                    "JOIN Lessons l ON ulp.lesson_id = l.id\n" +
                    "ORDER BY ulp.completed_at DESC;\n").mapToBean(UserLessonProgress.class).list();
        });
    }

    @Override
    public int update(UserLessonProgress entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }
}