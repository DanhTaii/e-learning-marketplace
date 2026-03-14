package vn.edu.nlu.fit.elearning.feature.lesson_progress.dao;

import vn.edu.nlu.fit.elearning.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.model.UserLessonProgress;

import java.util.List;

public class UserLessonProgressDaoImpl extends BaseDao implements UserLessonProgressDao {

    @Override
    public List<UserLessonProgress> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT usp.id, usp.user_id AS user_id ,l.id AS lesson_id, l.title AS lesson_title, L.order_index, usp.is_completed, l.duration_minutes \n" +
                    "FROM lessons l JOIN user_lesson_progress usp ON l.id = usp.lesson_id\n" +
                    "where l.course_id = 1 AND usp.user_id = 7\n" +
                    "ORDER BY l.order_index ASC").mapToBean(UserLessonProgress.class).list();
        });
    }

    @Override
    public List<LessonProgressDTO> findAllLessonProgress(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT usp.id, usp.user_id AS user_id ,l.id AS lesson_id, l.title AS lesson_title, " +
                            "l.order_index, usp.is_completed, l.duration_minutes, l.video_url \n" +
                            "FROM lessons l JOIN user_lesson_progress usp ON l.id = usp.lesson_id\n" +
                            "where l.course_id = :courseId AND usp.user_id = :userId\n" +
                            "ORDER BY l.order_index ASC")
                    .bind("userId", userId)
                    .bind("courseId", courseId)
                    .mapToBean(LessonProgressDTO.class).list();
        });
    }

    @Override
    public int createUserLessonProgress(List<UserLessonProgress> lessonProgressList) {
        String sql = "INSERT INTO user_lesson_progress (user_id, lesson_id) VALUES (:userId, :lessonId)";
        return getJdbi().withHandle(handle -> {
            var batch = handle.prepareBatch(sql);
            for (UserLessonProgress item : lessonProgressList) {
                batch.bindBean(item).add();

            }
            int[] results = batch.execute();
            return results.length;
        });

    }

    @Override
    public int updateLessonProgress(int id, boolean isCompleted) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE user_lesson_progress SET is_completed = :isCompleted WHERE id = :id")
                    .bind("id", id)
                    .bind("isCompleted", isCompleted)
                    .execute();
        });
    }

}