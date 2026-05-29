package vn.edu.nlu.fit.elearning.feature.order.model;

import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting.formatAndConvert;

public class Order implements Serializable {
    private int id;
    private String orderCode;
    private int userId;
    private String usernameSnapshot;
    private Integer paymentMethodId;
    private int totalAmount;
    private int discountAmount;
    private int voucherAmount;
    private int finalAmount;
    private OrderStatus status;
    private Timestamp paidAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderItem> items = new ArrayList<>();
    private Integer voucherId;
    private String voucherCode;
    public Order() {}
    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }
    public int getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(int voucherAmount) {
        this.voucherAmount = voucherAmount;
    }
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getPaidAt() { return paidAt; }
    public void setPaidAt(Timestamp paidAt) { this.paidAt = paidAt; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public String getUsernameSnapshot() {
        return usernameSnapshot;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public void setUsernameSnapshot(String usernameSnapshot) {
        this.usernameSnapshot = usernameSnapshot;
    }

    public String getFormatTotal(){
        return formatAndConvert(this.totalAmount);
  }
    public String getFormatDiscount(){
        return formatAndConvert(this.discountAmount);
    }
    public String getFormatFinal(){
        return formatAndConvert(this.finalAmount);
    }

}