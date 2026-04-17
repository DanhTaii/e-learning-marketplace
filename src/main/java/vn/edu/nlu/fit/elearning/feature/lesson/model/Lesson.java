package vn.edu.nlu.fit.elearning.feature.lesson.model;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class Lesson implements Serializable {

    private int id;

    private int courseId;

    private String title;

    private String videoUrl;

    private int durationMinutes;

    private int orderIndex;

    private BaseStatus status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Lesson() {
    }

    public Lesson(int id, int courseId, String title, String videoUrl, int durationMinutes, int orderIndex, BaseStatus status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.videoUrl = videoUrl;
        this.durationMinutes = durationMinutes;
        this.orderIndex = orderIndex;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
