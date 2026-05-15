package vn.edu.nlu.fit.elearning.feature.cart.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CartItemEntity implements Serializable {
    private int id;
    private int cartId;
    private int courseId;
    private Timestamp createdAt;

    public CartItemEntity() {
    }

    public CartItemEntity(int id, int cartId, int courseId, Timestamp createdAt) {
        this.id = id;
        this.cartId = cartId;
        this.courseId = courseId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
