package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Lesson implements Serializable {

    private int id;

    private int courseId;

    private String courseTitle;

    private String title;

    private String videoUrl;

    private int durationMinutes;

    private int orderIndex;

    private java.sql.Timestamp createdAt;

    public Lesson() {
    }

    public Lesson(int id, int courseId, String courseTitle, String title, String videoUrl, int durationMinutes, int orderIndex, java.sql.Timestamp createdAt) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.title = title;
        this.videoUrl = videoUrl;
        this.durationMinutes = durationMinutes;
        this.orderIndex = orderIndex;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
