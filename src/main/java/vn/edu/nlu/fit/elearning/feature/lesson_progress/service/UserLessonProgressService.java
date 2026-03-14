package vn.edu.nlu.fit.elearning.feature.lesson_progress.service;

import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.model.UserLessonProgress;

import java.util.List;

public interface UserLessonProgressService {
    int createUserLessonProgress(List<UserLessonProgress> userLessonProgress);

    List<LessonProgressDTO> getAllUserLessonProgresss(int userId, int courseId);

    UserLessonProgress getUserLessonProgressById(int id);

    int updateUserLessonProgress(int id, boolean isCompleted);

    void deleteUserLessonProgress(int id);

    void main(String[] args);
}
