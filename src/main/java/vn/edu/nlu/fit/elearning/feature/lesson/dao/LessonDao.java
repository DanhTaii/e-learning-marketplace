package vn.edu.nlu.fit.elearning.feature.lesson.dao;

import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.List;

public interface LessonDao {
    int create(Lesson entity);

    Lesson findById(Integer integer);

    List<Lesson> findAll();

    int update(Lesson entity);

    int delete(Integer lessonId);

    List<Lesson> findByName(String name);

    boolean checkExists(String title, int courseId);

    List<Lesson> findLessonsByFilter(String lessonName, String courseId);

    int updateWithReorder(Lesson lesson, int oldOrderIndex, int oldCourseId);

    List<Lesson> findByCourseId(int courseId);
}
