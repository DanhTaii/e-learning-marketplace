package vn.edu.nlu.fit.elearning.feature.lesson.dao;

import vn.edu.nlu.fit.elearning.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.List;

public interface LessonDao extends BaseCrudDao<Lesson, Integer> {
    @Override
    int create(Lesson entity);

    @Override
    Lesson findById(Integer integer);

    @Override
    List<Lesson> findAll();

    @Override
    int update(Lesson entity);

    @Override
    int delete(Integer lessonId);

    List<Lesson> findByName(String name);

    boolean checkExists(String title, int courseId);

    List<Lesson> findLessonsByFilter(String lessonName, String courseId);

    int updateWithReorder(Lesson lesson, int oldOrderIndex, int oldCourseId);

    List<Lesson> findByCourseId(int courseId);
}
