package vn.edu.nlu.fit.elearning.dto;

public class LessonProgressDTO {
    // Từ bảng Lessons
    private int lessonId;
    private String title;
    private int durationMinutes;
    private int orderIndex;

    // Từ bảng User_Lesson_Progress
    private boolean isCompleted;

}
