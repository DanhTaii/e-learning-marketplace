package vn.edu.nlu.fit.elearning.feature.lesson_progress.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserLessonProgress implements Serializable {
    private int id;
    private int userId;
    private int lessonId;
    private int lastWatchedTime;
    private boolean completed; // Map từ is_completed
    private Timestamp completedAt;

    // 1. Constructor rỗng (Bắt buộc)
    public UserLessonProgress() {
    }

    // 2. Constructor đầy đủ
    public UserLessonProgress(int id, int userId, int lessonId, boolean completed, Timestamp completedAt) {
        this.id = id;
        this.userId = userId;
        this.lessonId = lessonId;
        this.completed = completed;
        this.completedAt = completedAt;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getLessonId() { return lessonId; }
    public void setLessonId(int lessonId) { this.lessonId = lessonId; }

    public int getLastWatchedTime() {
        return lastWatchedTime;
    }

    public void setLastWatchedTime(int lastWatchedTime) {
        this.lastWatchedTime = lastWatchedTime;
    }

    // Getter cho boolean thường đặt là is...
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public Timestamp getCompletedAt() { return completedAt; }
    public void setCompletedAt(Timestamp completedAt) { this.completedAt = completedAt; }
}