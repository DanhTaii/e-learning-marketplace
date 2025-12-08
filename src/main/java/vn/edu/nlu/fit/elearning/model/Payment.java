package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Payment implements Serializable {
    private int id;
    private int orderId;
    private int paymentMethodId;
    private double amount;
    private String transactionId; // Map từ transaction_id
    private String status;        // ENUM -> String
    private Timestamp createdAt;

    public Payment() {
    }

    public Payment(int id, int orderId, int paymentMethodId, double amount, String transactionId, String status, Timestamp createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.createdAt = createdAt;
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

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}