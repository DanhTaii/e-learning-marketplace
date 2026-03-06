package vn.edu.nlu.fit.elearning.feature.enrollment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Enrollment implements Serializable {
    private int id;
    private int userId;
    private int courseId;
    private int orderId;
    private Timestamp enrolledAt;

    public Enrollment() {
    }

    public Enrollment(int id, int userId, int courseId, int orderId, Timestamp enrolledAt) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.orderId = orderId;
        this.enrolledAt = enrolledAt;
    }

    // Constructor để thêm mới
    public Enrollment(int userId, int courseId, int orderId) {
        this.userId = userId;
        this.courseId = courseId;
        this.orderId = orderId;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Timestamp getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(Timestamp enrolledAt) { this.enrolledAt = enrolledAt; }
}