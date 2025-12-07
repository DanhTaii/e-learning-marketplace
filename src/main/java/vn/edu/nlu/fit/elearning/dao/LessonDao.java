package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Lesson;

import java.util.List;

public class LessonDao extends BaseDao implements BaseCrudDao<Lesson, Integer> {
    @Override
    public void create(Lesson entity) {

    }

    @Override
    public Lesson findById(Integer integer) {
        return null;
    }

    @Override
    public List<Lesson> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT  l.title, l.order_index, c.title AS course_title, l.video_url, l.duration_minutes, l.created_at\n" +
                    "FROM Lessons l JOIN Courses c ON l.course_id = c.id\n" +
                    "ORDER BY l.order_index ASC;").mapToBean(Lesson.class).list();
        });
    }

    @Override
    public int update(Lesson entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }
}
