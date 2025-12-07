
package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.LessonDao;
import vn.edu.nlu.fit.elearning.model.Lesson;

import java.util.List;

public class LessonService {

    private LessonDao lessonDao;

    public LessonService() {
        this.lessonDao = new LessonDao();
    }

    public int createLesson(Lesson lesson) {
        return 0;
    }

    public List<Lesson> getAllLessons() {
        return lessonDao.findAll();
    }

    public Lesson getLessonById(int id) {
        return null;
    }

    public void updateLesson(Lesson lesson) {

    }

    public void deleteLesson(int id) {
    }

}
