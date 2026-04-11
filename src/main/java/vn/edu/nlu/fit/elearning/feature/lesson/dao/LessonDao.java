package vn.edu.nlu.fit.elearning.feature.lesson.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonDao {
    int create(Lesson entity);

    Lesson findById(Integer integer);

    List<Lesson> findAll();

    int update(Lesson entity);

    int delete(Integer lessonId);

    List<Lesson> findByName(String name);

    boolean checkExists(String title, int courseId);

    int updateWithReorder(Lesson lesson, int oldOrderIndex, int oldCourseId);

    List<Lesson> findByCourseId(int courseId);

    List<Lesson> findLessonsByFilter(LessonFilter filter);

    int countLessonsByFilter(LessonFilter  filter);

}
