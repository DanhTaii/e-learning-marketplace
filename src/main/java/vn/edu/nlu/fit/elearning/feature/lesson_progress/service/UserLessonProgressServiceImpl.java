package vn.edu.nlu.fit.elearning.feature.lesson_progress.service;

import vn.edu.nlu.fit.elearning.feature.lesson_progress.dao.UserLessonProgressDao;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dao.UserLessonProgressDaoImpl;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.model.UserLessonProgress;

import java.util.List;

public class UserLessonProgressServiceImpl implements UserLessonProgressService {

    private UserLessonProgressDao ulpd;

    public UserLessonProgressServiceImpl(UserLessonProgressDao userLessonProgressDao) {
        this.ulpd = userLessonProgressDao;
    }

    @Override
    public int createUserLessonProgress(List<UserLessonProgress> userLessonProgress) {
       return ulpd.createUserLessonProgress(userLessonProgress);
    }

    @Override
    public List<LessonProgressDTO> getAllUserLessonProgresss(int userId, int courseId) {
        return ulpd.findAllLessonProgress(userId, courseId);
    }

    @Override
    public UserLessonProgress getUserLessonProgressById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    @Override
    public int updateUserLessonProgress(int id, boolean isCompleted) {
        return this.ulpd.updateLessonProgress(id, isCompleted);
    }

    @Override
    public void deleteUserLessonProgress(int id) {
        // TODO: Implement delete logic
    }

//    @Override
//    public void main(String[] args) {
////        List<LessonProgressDTO> result = this.getAllUserLessonProgresss(7,7);
//        System.out.println(this.getAllUserLessonProgresss(3, 1));
//    }

}