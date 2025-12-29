package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;

public class OrderItem implements Serializable {
    private int id;
    private int orderId;
    private int courseId;
    private int priceAtPurchase;    // Đổi từ double → int (theo DB: INT NOT NULL)
    private Timestamp createdAt;
    private Timestamp updatedAt;
    // Field tạm để chọn trong giao diện (nếu cần)
    private boolean selected;

    public OrderItem() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(int priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }

    // Format giá khóa học tại thời điểm mua
    public String getPriceAtPurchaseFormatted() {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(vietnam);
        String formatted = formatter.format(priceAtPurchase);
        return formatted.replace(" ₫", "").replace("₫", "").trim();
    }
}