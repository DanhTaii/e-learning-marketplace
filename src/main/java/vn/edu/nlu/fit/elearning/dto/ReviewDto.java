package vn.edu.nlu.fit.elearning.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReviewDto implements Serializable {
    private int id;
    private int userId;
    private int courseId;
    private double rating;
    private String comment;
    private Timestamp createdAt;
    private String userName;
    private String thumbnailUrl;

    public ReviewDto() {
    }

    public ReviewDto(int id, int userId, int courseId, double rating, String comment, Timestamp createdAt, String userName, String thumbnailUrl) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.userName = userName;
        this.thumbnailUrl = thumbnailUrl;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
