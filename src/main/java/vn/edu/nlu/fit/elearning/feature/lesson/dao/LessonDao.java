package vn.edu.nlu.fit.elearning.feature.lesson.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonArchiveFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.dto.LessonArchive;

import java.util.List;

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

    int countAllLessons();

    int deleteLessonByIds(List<Integer> ids);

    int findMaxOrderIndexByCourseId(int courseId);

    int updateLessonsStatusByIds(List<Integer> ids);

//    =================== ARCHIVE FUNCTION ========================
    int countAllLessonsArchive();

    int archivedLessonsByIds(List<Integer> ids, String deleteReason);

    List<LessonArchive> findArchivedLessonsByFilter(LessonArchiveFilter filter);

    int countLessonsArchiveByFilter(LessonArchiveFilter filter);

    int restoreLessonsByIds(List<Integer> ids);

}
