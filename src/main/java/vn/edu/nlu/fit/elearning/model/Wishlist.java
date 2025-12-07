package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.sql.Timestamp; // <--- Import cái này

public class Wishlist implements Serializable {
    private int id;
    private int userId;
    private int courseId;
    private Timestamp addedAt; // <--- Dùng Timestamp thay vì LocalDateTime

    public Wishlist() {
    }

    public Wishlist(int id, int userId, int courseId, Timestamp addedAt) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.addedAt = addedAt;
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

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }
}