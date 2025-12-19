package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.Tag;

import java.util.List;

public class LessonDao extends BaseDao implements BaseCrudDao<Lesson, Integer> {
    @Override
    public void create(Lesson entity) {
        String sql = "INSERT INTO Lessons (course_id , title, video_url, duration_minutes, order_index) \n" +
                "VALUES (:courseId, :title , :videoUrl , :durationMinutes, " +
                "(SELECT COALESCE(MAX(order_index), 0) + 1 FROM Lessons l WHERE l.course_id = :courseId))";
        getJdbi().useHandle(handle -> {
            handle.prepareBatch(sql)
                    .bindBean(entity).add()
                    .execute();
        });
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

    public List<Lesson> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT l.title, l.order_index, l.duration_minutes, l.created_at " +
                            "FROM Lessons l " +
                            "WHERE l.title LIKE :nameSearch " +
                            "GROUP BY l.id")
                    .bind("nameSearch", nameSearch).mapToBean(Lesson.class).list();
        });
    }

    }

