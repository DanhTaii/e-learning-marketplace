package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable {
    private int id;
    private int userId;
    private int courseId;
    private double rating;   // Map với DECIMAL(3,2)
    private String comment;
    private LocalDateTime createdAt; // Map với DATETIME


    public Review() {
    }


    public Review(int id, int userId, int courseId, double rating, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    // 3. Constructor để tạo review mới (không cần id và createdAt vì DB tự sinh)
    public Review(int userId, int courseId, double rating, String comment) {
        this.userId = userId;
        this.courseId = courseId;
        this.rating = rating;
        this.comment = comment;
    }

    // --- GETTER & SETTER ---

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}