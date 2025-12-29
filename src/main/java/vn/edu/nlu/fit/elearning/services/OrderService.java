package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.OrderDao;
import vn.edu.nlu.fit.elearning.model.Order;

import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        if (order != null) {
            orderDao.create(order);
            return 1;
        }
        return 0;
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order getOrderById(int orderId) {
        return orderDao.findById(orderId);
    }

    public Order findOrderPending(Integer userId) {
        if (userId == null) {
            return null;
        }
        return orderDao.findOrderPending(userId);
    }

    public int updateOrder(Order order) {
        if (order != null) {
            return orderDao.update(order);
        }
        return 0;
    }


    public boolean deleteOrder(int orderId) {
        int status = orderDao.delete(orderId);
        return status > 0;
    }

    public double getRevenueTotal() {
        return orderDao.calculateRevenueTotal();
    }
}