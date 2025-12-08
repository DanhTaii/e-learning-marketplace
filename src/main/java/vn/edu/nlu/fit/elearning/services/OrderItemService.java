package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.model.OrderItem;

import java.util.List;

public class OrderItemService {

    private OrderItemDao oid;

    public OrderItemService() {
        this.oid = new OrderItemDao();
    }

    public List<OrderItem> getOrderItemList(int orderId){
        List<OrderItem> orderItemList = oid.getOrderItemList(orderId);
        return orderItemList;
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