package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.CourseTag;

import java.util.List;

public class CourseTagDao extends BaseDao implements BaseCrudDao<CourseTag, Integer> {

    @Override
    public int create(CourseTag entity) {
        // TODO: Implement create logic
        return 0;
    }

    @Override
    public CourseTag findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<CourseTag> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ct.id AS course_tag_id, ct.course_id, c.title AS course_title, c.subtitle, c.price, ct.tag_id, t.name AS tag_name, t.slug AS tag_slug, t.created_at AS tag_created_at\n" +
                    "FROM Course_Tags ct\n" +
                    "JOIN Courses c ON ct.course_id = c.id\n" +
                    "JOIN Tags t ON ct.tag_id = t.id;").mapToBean(CourseTag.class).list();
        });
    }

    @Override
    public int update(CourseTag entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }

    public List<Integer> findTagIdByCourseId(int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT tag_id\n" +
                    "FROM course_tags\n" +
                    "WHERE course_id = :courseId\n")
                    .bind("courseId", courseId)
                    .mapTo(Integer.class)
                    .list();
        });
    }

}