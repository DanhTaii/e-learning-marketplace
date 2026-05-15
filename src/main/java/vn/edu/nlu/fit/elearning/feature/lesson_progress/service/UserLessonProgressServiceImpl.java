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
    public List<LessonProgressDTO> getAllUserLessonProgresses(int userId, int courseId) {
        return ulpd.findAllLessonProgress(userId, courseId);
    }

    @Override
    public UserLessonProgress getUserLessonProgressById(int id) {
        return null;
    }

    @Override
    public int updateUserLessonProgress(int id, boolean isCompleted) {
        return this.ulpd.updateLessonProgress(id, isCompleted);
    }

    @Override
    public void deleteUserLessonProgress(int id) {
    }

    @Override
    public int updateUserLessonProgressLastWatchedTime(int userId, int lessonId, int lastWatchedTime) {
        int lessonDuration = this.getLessonDurationMinutesById(lessonId);

        if (lessonDuration <= 0) {
            return 0;
        }

        //Do lúc lưu thời gian xem cuối thì JS lấy ra giây chứ không lấy theo phút
        //Mà lesson đang lưu phút nên phải nhân cho 60s
        int lessonDurationInSecond = lessonDuration * 60;

        if (lastWatchedTime > lessonDurationInSecond) {
            lastWatchedTime = lessonDurationInSecond;
        }

        return this.ulpd.updateLastWatchedTime(userId, lessonId, lastWatchedTime);
    }

    @Override
    public int getLessonDurationMinutesById(int lessonId) {
        return ulpd.findDurationMinutesByLessonId(lessonId);
    }

    @Override
    public int getUserLessonProgressLastWatchedTime(int userId, int lessonId) {
        return ulpd.findLastWatchedTimeById(userId, lessonId);
    }

    public static void main(String[] args) {
        try {
            UserLessonProgressServiceImpl userLessonProgressService = new UserLessonProgressServiceImpl(new UserLessonProgressDaoImpl());

            System.out.println(userLessonProgressService.getLessonDurationMinutesById(6));

            userLessonProgressService.updateUserLessonProgressLastWatchedTime(73, 6, 15 * 60);

            System.out.println(userLessonProgressService.getLessonDurationMinutesById(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}