package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.OrderItem;

import java.util.List;

public class OrderItemService {

    private OrderItemDao oid;

    public OrderItemService() {
        this.oid = new OrderItemDao();
    }

    public List<OrderItemDTO> getCartItems(Integer userId) {
        List<OrderItemDTO> cartItems = oid.getCartItemsByUserId(userId);
        return cartItems;
    }

    public List<OrderItemDTO> getOrderItemSelected(Integer userId){
        List<OrderItemDTO> itemSelected = oid.geOrderItemSelected(userId);
        return itemSelected;
    }

    public int createOrderItem(OrderItem orderItem) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<OrderItem> getAllOrderItems() {
        // TODO: Implement getAll logic
        return oid.findAll();
    }

    public OrderItem getOrderItemById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateOrderItem(OrderItem orderItem) {
        // TODO: Implement update logic
    }

    public void deleteOrderItem(int id) {
        // TODO: Implement delete logic
    }
}