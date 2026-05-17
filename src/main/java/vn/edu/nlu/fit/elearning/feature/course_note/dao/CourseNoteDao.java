package vn.edu.nlu.fit.elearning.feature.course_note.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.course_note.model.CourseNote;

import java.util.List;

public interface CourseNoteDao extends BaseCrudDao<CourseNote, Integer> {
    List<CourseNote> findByUserIdAndLessonId(int userId, int lessonId);

    int updateContentById(int id, String content);
}
