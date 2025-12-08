package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.UserLessonProgressDao;
import vn.edu.nlu.fit.elearning.model.UserLessonProgress;

import java.util.List;

public class UserLessonProgressService {

    private UserLessonProgressDao ulpd;

    public UserLessonProgressService() {
        this.ulpd = new UserLessonProgressDao();
    }

    public int createUserLessonProgress(UserLessonProgress userLessonProgress) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<UserLessonProgress> getAllUserLessonProgresss() {
        // TODO: Implement getAll logic
        return ulpd.findAll();
    }

    public UserLessonProgress getUserLessonProgressById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateUserLessonProgress(UserLessonProgress userLessonProgress) {
        // TODO: Implement update logic
    }

    public void deleteUserLessonProgress(int id) {
        // TODO: Implement delete logic
    }
}