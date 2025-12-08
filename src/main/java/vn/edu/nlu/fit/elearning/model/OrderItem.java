package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int id;
    private int orderId;
    private int courseId;
    private double priceAtPurchase; // Giá tại thời điểm mua (quan trọng)
    private String title;
    private String thumbnailUrl;

    public OrderItem() {
    }

    public OrderItem(int id, int orderId, int courseId, double priceAtPurchase) {
        this.id = id;
        this.orderId = orderId;
        this.courseId = courseId;
        this.priceAtPurchase = priceAtPurchase;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public double getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(double priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}