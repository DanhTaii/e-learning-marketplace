package vn.edu.nlu.fit.elearning.dto;

public class LessonProgressDTO {
    // Từ bảng Lessons
    private int id;
    private int userId;
    private int lessonId;
    private int orderIndex;
    private String lessonTitle;
    private boolean isCompleted;
    private int durationMinutes;
    private String videoUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "LessonProgressDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", lessonId=" + lessonId +
                ", orderIndex=" + orderIndex +
                ", lessonTitle='" + lessonTitle + '\'' +
                ", isCompleted=" + isCompleted +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}
