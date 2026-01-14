package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.UserLessonProgressDao;
import vn.edu.nlu.fit.elearning.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.model.UserLessonProgress;

import java.util.List;

public class UserLessonProgressService {

    private UserLessonProgressDao ulpd;

    public UserLessonProgressService() {
        this.ulpd = new UserLessonProgressDao();
    }

    public int createUserLessonProgress(List<UserLessonProgress> userLessonProgress) {
       return ulpd.createUserLessonProgress(userLessonProgress);
    }

    public List<LessonProgressDTO> getAllUserLessonProgresss(int userId, int courseId) {
        return ulpd.findAllLessonProgress(userId, courseId);
    }

    public UserLessonProgress getUserLessonProgressById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public int updateUserLessonProgress(int id, boolean isCompleted) {
        return this.ulpd.updateLessonProgress(id, isCompleted);
    }

    public void deleteUserLessonProgress(int id) {
        // TODO: Implement delete logic
    }

    public void main(String[] args) {
//        List<LessonProgressDTO> result = this.getAllUserLessonProgresss(7,7);
        System.out.println(this.getAllUserLessonProgresss(3, 1));
    }

}