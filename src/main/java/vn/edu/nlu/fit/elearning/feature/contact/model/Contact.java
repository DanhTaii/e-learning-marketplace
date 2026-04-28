package vn.edu.nlu.fit.elearning.feature.contact.model;

import vn.edu.nlu.fit.elearning.common.helper.enums.ContactStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Contact implements Serializable {
    private int id;
    private Integer userId;
    private String email;
    private String subject;
    private String message;
    private ContactStatus status;
    private String adminReply;
    private String trackingToken;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp resolvedAt;

    public Contact() {
    }

    public Contact(int id, Integer userId, String email, String subject, String message, ContactStatus status, String adminReply, String trackingToken, Timestamp createdAt, Timestamp updatedAt, Timestamp resolvedAt) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.adminReply = adminReply;
        this.trackingToken = trackingToken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.resolvedAt = resolvedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactStatus getStatus() {
        return status;
    }

    public void setStatus(ContactStatus status) {
        this.status = status;
    }

    public String getAdminReply() {
        return adminReply;
    }

    public void setAdminReply(String adminReply) {
        this.adminReply = adminReply;
    }

    public String getTrackingToken() {
        return trackingToken;
    }

    public void setTrackingToken(String trackingToken) {
        this.trackingToken = trackingToken;
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

    public Timestamp getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Timestamp resolvedAt) {
        this.resolvedAt = resolvedAt;
    }
}
