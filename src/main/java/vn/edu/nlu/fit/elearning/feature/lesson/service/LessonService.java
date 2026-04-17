package vn.edu.nlu.fit.elearning.feature.lesson.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.List;

public interface LessonService {
    int createLesson(Lesson lesson);

    List<Lesson> getAllLessons();

    Lesson getLessonById(int id);

    int updateLesson(Lesson lesson);

    int deleteLesson(int id);

    List<Lesson> getAllTagsByName(String name);

    boolean checkLessonName(String title, int courseId);

    List<Lesson> getLessonsByFilter(LessonFilter filter);

    int getCountLessonsByFilter(LessonFilter filter);

    boolean updateLessonWithOrdering(Lesson lesson, int oldOrderIndex, int oldCourseId);

    List<Lesson> getLessonsByCourseId(int courseId);

    int getTotalLessons();
}
