
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
        if (lesson != null) {
            lessonDao.create(lesson);
            return 1;
        };

        return 0;
    }

    public List<Lesson> getAllLessons() {
        System.out.println(lessonDao.findAll());
        return lessonDao.findAll();
    }

    public Lesson getLessonById(int id) {
        return null;
    }

    public void updateLesson(Lesson lesson) {

    }

    public void deleteLesson(int id) {
    }

    public List<Lesson> getAllTagsByName(String name) {
        return lessonDao.findByName(name);
    }
}
