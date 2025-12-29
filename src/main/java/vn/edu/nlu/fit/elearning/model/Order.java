package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order implements Serializable {
    private int id;
    private String orderCode;
    private int userId;
    private Integer paymentMethodId;
    private int totalAmount;
    private int discountAmount;
    private int finalAmount;
    private String status;
    private Timestamp paidAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Integer getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(Integer paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }

    public int getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(int discountAmount) { this.discountAmount = discountAmount; }

    public int getFinalAmount() { return finalAmount; }
    public void setFinalAmount(int finalAmount) { this.finalAmount = finalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getPaidAt() { return paidAt; }
    public void setPaidAt(Timestamp paidAt) { this.paidAt = paidAt; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public String getTotalAmountFormatted() {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat f = NumberFormat.getCurrencyInstance(vietnam);
        String formatted = f.format(this.totalAmount);
        // Mặc định nó ra "300.000 đ", nếu muốn bỏ chữ "đ" đi để tự thêm sau thì:
        return formatted.replace(" ₫", "").replace("₫", "").trim();
    }

    // Làm tương tự cho giá cũ
    public String getFinalAmountFormatted() {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat f = NumberFormat.getCurrencyInstance(vietnam);
        String formatted = f.format(this.finalAmount);
        return formatted.replace(" ₫", "").replace("₫", "").trim();
    }
}