package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PaymentMethod implements Serializable {

    private int id;
    private String name;
    private String code;
    private String iconUrl;
    private String status;          // 'ACTIVE' hoặc 'INACTIVE'
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor rỗng
    public PaymentMethod() {
    }

    // Constructor đầy đủ (nếu cần dùng sau này)
    public PaymentMethod(int id, String name, String code, String iconUrl,
                         String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.iconUrl = iconUrl;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- GETTER & SETTER ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }
}