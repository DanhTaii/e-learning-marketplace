package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.OrderDao;
import vn.edu.nlu.fit.elearning.model.Order;

import java.util.List;

public class OrderService {

    private OrderDao od;

    public OrderService() {
        this.od = new OrderDao();
    }

    public int createOrder(Order order) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<Order> getAllOrders() {
        // TODO: Implement getAll logic
        return od.findAll();
    }

    public Order getOrderById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateOrder(Order order) {
        // TODO: Implement update logic
    }

    public void deleteOrder(int id) {
        // TODO: Implement delete logic
    }
}