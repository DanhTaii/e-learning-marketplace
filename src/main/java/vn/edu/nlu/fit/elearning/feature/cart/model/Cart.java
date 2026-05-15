package vn.edu.nlu.fit.elearning.feature.cart.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private int id;
    private int userId;
    private String cartHash;
    private Timestamp updatedAt;
    private List<CartItem> items;
    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(int id, int userId, String cartHash, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.cartHash = cartHash;
        this.updatedAt = updatedAt;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCartHash() {
        return cartHash;
    }

    public void setCartHash(String cartHash) {
        this.cartHash = cartHash;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
