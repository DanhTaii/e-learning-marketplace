package vn.edu.nlu.fit.elearning.feature.course_note.service;

import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.course_note.model.CourseNote;

import java.util.List;

public interface CourseNoteService {
    int createCourseNotes(CourseNote courseNote);

    List<CourseNote> getNotesByUserIdAndLessonId(int userId, int lessonId);

    int deleteCourseNotes(int id);
}
