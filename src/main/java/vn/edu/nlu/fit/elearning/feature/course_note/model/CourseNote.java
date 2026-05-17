package vn.edu.nlu.fit.elearning.feature.course_note.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseNote implements Serializable {
    private int id;
    private int userId;
    private int lessonId;
    private int noteTime; // giây
    private String content;
    private Timestamp createdAt;

    public CourseNote() {
    }

    // Thêm một trường tiện ích để hiển thị lên giao diện (ví dụ: "02:15")
    public String getFormattedTime() {
        int minutes = noteTime / 60;
        int seconds = noteTime % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

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

    public int getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(int noteTime) {
        this.noteTime = noteTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}