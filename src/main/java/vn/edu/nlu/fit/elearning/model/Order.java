package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
    private int id;
    private String orderCode;
    private int userId;
    private double totalAmount;
    private double discountAmount;
    private double finalAmount;
    private String status;         // Map ENUM ('pending', 'paid'...) thành String
    private Timestamp paidAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Order() {
    }

    public Order(int id, String orderCode, int userId, double totalAmount, double discountAmount, double finalAmount, String status, Timestamp paidAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.orderCode = orderCode;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.status = status;
        this.paidAt = paidAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getPaidAt() { return paidAt; }
    public void setPaidAt(Timestamp paidAt) { this.paidAt = paidAt; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}