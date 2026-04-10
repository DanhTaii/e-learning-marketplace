
package vn.edu.nlu.fit.elearning.feature.lesson.service;

import vn.edu.nlu.fit.elearning.feature.lesson.dao.LessonDao;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public int createLesson(Lesson lesson) {
        try {
            return lessonDao.create(lesson);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonDao.findAll();
    }

    @Override
    public Lesson getLessonById(int id) {
        return lessonDao.findById(id);
    }

    @Override
    public int updateLesson(Lesson lesson) {
return  lessonDao.update(lesson);
    }

    @Override
    public int deleteLesson(int id) {
    return lessonDao.delete(id);
    }

    @Override
    public List<Lesson> getAllTagsByName(String name) {
        return lessonDao.findByName(name);
    }

    @Override
    public boolean checkLessonName(String title, int courseId) {
        return lessonDao.checkExists(title, courseId);
    }

    @Override
    public List<Lesson> getSearchLessons(String lessonName, String courseId) {
        return lessonDao.findLessonsByFilter(lessonName,courseId );
    }
    @Override
    public boolean updateLessonWithOrdering(Lesson lesson, int oldOrderIndex, int oldCourseId) {

        return lessonDao.updateWithReorder(lesson, oldOrderIndex,oldCourseId) > 0;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(int courseId) {
        return lessonDao.findByCourseId(courseId);
    }

}
