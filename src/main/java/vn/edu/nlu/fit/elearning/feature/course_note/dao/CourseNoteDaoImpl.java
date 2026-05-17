package vn.edu.nlu.fit.elearning.feature.course_note.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.course_note.model.CourseNote;

import java.util.List;

public class CourseNoteDaoImpl extends BaseDao implements CourseNoteDao {

    @Override
    public int create(CourseNote entity) {
        String sql = "INSERT INTO course_notes (user_id, lesson_id, note_time, content) VALUES (:userId, :lessonId, :noteTime, :content)";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(entity)
                        .executeAndReturnGeneratedKeys()
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public CourseNote findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<CourseNote> findAll() {
        return null;
    }

    @Override
    public int update(CourseNote entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM course_notes WHERE id = :id";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("id", id)
                        .execute()
        );
    }

    @Override
    public List<CourseNote> findByUserIdAndLessonId(int userId, int lessonId) {
        String sql = "SELECT * FROM course_notes WHERE lesson_id = :lessonId AND user_id = :userId ORDER BY note_time ASC";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("lessonId", lessonId)
                        .bind("userId", userId)
                        .mapToBean(CourseNote.class)
                        .list()
        );
    }

    @Override
    public int updateContentById(int id, String content) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE course_notes SET content = :content WHERE id = :id")
                    .bind("id", id)
                    .bind("content", content)
                    .execute();
        });
    }
}