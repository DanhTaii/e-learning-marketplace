package vn.edu.nlu.fit.elearning.feature.course_tag.dao;

import vn.edu.nlu.fit.elearning.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.course_tag.model.CourseTag;

import java.util.List;

public class CourseTagDao extends BaseDao {

    public int create(int courseId, int tagId) {
        // TODO: Implement create logic
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO course_tags ( course_id, tag_id) VALUES (:courseId, :tagId)")
                    .bind("courseId", courseId)
                    .bind("tagId", tagId)
                    .execute();
        });
    }

    public CourseTag findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    public List<CourseTag> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ct.id AS course_tag_id, ct.course_id, c.title AS course_title, c.subtitle, c.price, ct.tag_id, t.name AS tag_name, t.slug AS tag_slug, t.created_at AS tag_created_at\n" +
                    "FROM course_tags ct\n" +
                    "JOIN courses c ON ct.course_id = c.id\n" +
                    "JOIN Tags t ON ct.tag_id = t.id;").mapToBean(CourseTag.class).list();
        });
    }

    public int update(CourseTag entity) {
        // TODO: Implement update logic
        return 0;
    }

    public int delete(Integer id) {
        // TODO: Implement delete logic
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM course_tags WHERE course_id = :courseId")
                    .bind("courseId", id)
                    .execute();
        });
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