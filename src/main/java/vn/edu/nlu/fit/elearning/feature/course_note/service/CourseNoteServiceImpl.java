package vn.edu.nlu.fit.elearning.feature.course_note.service;

import vn.edu.nlu.fit.elearning.feature.course_note.dao.CourseNoteDao;
import vn.edu.nlu.fit.elearning.feature.course_note.model.CourseNote;

import java.util.List;

public class CourseNoteServiceImpl implements CourseNoteService {

    private final CourseNoteDao courseNoteDao;

    public CourseNoteServiceImpl(CourseNoteDao courseNoteDao) {
        this.courseNoteDao = courseNoteDao;
    }

    @Override
    public int createCourseNotes(CourseNote courseNote) {
        return courseNoteDao.create(courseNote);
    }

    @Override
    public List<CourseNote> getNotesByUserIdAndLessonId(int userId, int lessonId) {
        return courseNoteDao.findByUserIdAndLessonId(userId, lessonId);
    }

    @Override
    public int deleteCourseNotes(int id) {
        return 0;
    }
}
