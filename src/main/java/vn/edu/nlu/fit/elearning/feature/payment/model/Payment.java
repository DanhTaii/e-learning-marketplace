package vn.edu.nlu.fit.elearning.feature.payment.model;

import vn.edu.nlu.fit.elearning.common.helper.enums.PaymentStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class Payment implements Serializable {
    private int id;
    private int orderId;
    private int paymentMethodId;
    private String gateway_transaction_id;
    private double amount;

    private PaymentStatus status;
    private Timestamp createdAt;
    private Timestamp updateAt;

    public Payment() {
    }

    public Payment(int id, int orderId, int paymentMethodId, String gateway_transaction_id, double amount, PaymentStatus status, Timestamp createdAt, Timestamp updateAt) {
        this.id = id;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
        this.gateway_transaction_id = gateway_transaction_id;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(int paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getGateway_transaction_id() {
        return gateway_transaction_id;
    }

    public void setGateway_transaction_id(String gateway_transaction_id) {
        this.gateway_transaction_id = gateway_transaction_id;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}