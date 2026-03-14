package vn.edu.nlu.fit.elearning.feature.lesson_progress.dao;

import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.model.UserLessonProgress;

import java.util.List;

public interface UserLessonProgressDao {
    List<UserLessonProgress> findAll();

    List<LessonProgressDTO> findAllLessonProgress(int userId, int courseId);

    int createUserLessonProgress(List<UserLessonProgress> lessonProgressList);

    int updateLessonProgress(int id, boolean isCompleted);
}
