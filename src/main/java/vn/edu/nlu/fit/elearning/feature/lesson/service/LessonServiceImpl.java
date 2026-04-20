
package vn.edu.nlu.fit.elearning.feature.lesson.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.common.utils.StringUtils;
import vn.edu.nlu.fit.elearning.feature.lesson.dao.LessonDao;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return lessonDao.update(lesson);
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
    public List<Lesson> getLessonsByFilter(LessonFilter filter) {
        return lessonDao.findLessonsByFilter(filter);
    }

    @Override
    public int getCountLessonsByFilter(LessonFilter filter) {
        return lessonDao.countLessonsByFilter(filter);
    }

    @Override
    public boolean updateLessonWithOrdering(Lesson lesson, int oldOrderIndex, int oldCourseId) {

        return lessonDao.updateWithReorder(lesson, oldOrderIndex, oldCourseId) > 0;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(int courseId) {
        return lessonDao.findByCourseId(courseId);
    }

    @Override
    public int getTotalLessons() {
        return lessonDao.countAllLessons();
    }

    @Override
    public int deleteLessonByids(List<Integer> ids) {
        return lessonDao.deleteLessonByIds(ids);
    }

    @Override
    public int getMaxOrderIndexByCourseId(int courseId) {
        return lessonDao.findMaxOrderIndexByCourseId(courseId);
    }


    @Override
    public int bulkDuplicateLessons(List<Integer> ids) {
        int count = 0;
        Map<Integer, Integer> courseMaxOrderIndexMap = new HashMap<>();

        for (Integer id : ids) {
            Lesson original = lessonDao.findById(id);
            if (original != null) {
                int courseId = original.getCourseId();

                if (!courseMaxOrderIndexMap.containsKey(courseId)) {
                    int originalMaxOrderIndex = getMaxOrderIndexByCourseId(courseId);
                    courseMaxOrderIndexMap.put(courseId, originalMaxOrderIndex);
                }

                int nextOrder = courseMaxOrderIndexMap.get(courseId) + 1;
                courseMaxOrderIndexMap.put(courseId, nextOrder);

                Lesson clone = new Lesson();
                clone.setStatus(BaseStatus.INACTIVE);
                clone.setOrderIndex(nextOrder);

                String lessonTitle = original.getTitle();
                String newLessonTitle = StringUtils.generateCloneTitle(lessonTitle, "Bản sao");
                clone.setTitle(newLessonTitle);
                clone.setDurationMinutes(original.getDurationMinutes());
                clone.setVideoUrl(original.getVideoUrl());
                clone.setCourseId(courseId);

                if (lessonDao.create(clone) > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int changeLessonsStatusByIds(List<Integer> ids) {
        return lessonDao.updateLessonsStatusByIds(ids);
    }

}
