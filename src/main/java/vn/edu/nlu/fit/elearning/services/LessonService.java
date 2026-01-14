
package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.LessonDao;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.User;

import java.util.List;

public class LessonService {

    private LessonDao lessonDao;

    public LessonService() {
        this.lessonDao = new LessonDao();
    }

    public int createLesson(Lesson lesson) {
        if (lesson != null) {
            lessonDao.create(lesson);
            return 1;
        };

        return 0;
    }

    public List<Lesson> getAllLessons() {
        return lessonDao.findAll();
    }

    public Lesson getLessonById(int id) {
        return lessonDao.findById(id);
    }

    public int updateLesson(Lesson lesson) {
return  lessonDao.update(lesson);
    }

    public int deleteLesson(int id) {
    return lessonDao.delete(id);
    }

    public List<Lesson> getAllTagsByName(String name) {
        return lessonDao.findByName(name);
    }

    public boolean checkLessonName(String title, int courseId) {
        return lessonDao.checkExists(title, courseId);
    }

    public List<Lesson> getSearchLessons(String lessonName, String courseId) {
        return lessonDao.findLessonsByFilter(lessonName,courseId );
    }
    public boolean updateLessonWithOrdering(Lesson lesson, int oldOrderIndex, int oldCourseId) {

        return lessonDao.updateWithReorder(lesson, oldOrderIndex,oldCourseId) > 0;
    }

    public List<Lesson> getLessonsByCourseId(int courseId) {
        return lessonDao.findByCourseId(courseId);
    }

}
