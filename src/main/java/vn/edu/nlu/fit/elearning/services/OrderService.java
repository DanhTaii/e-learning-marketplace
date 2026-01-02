package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.OrderDao;
import vn.edu.nlu.fit.elearning.model.Order;

import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class OrderService {

    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
       return  orderDao.create(order);

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

    public List<Map<String, Object>> searchOrders(String orderCode, String userName, Timestamp fromDate, String status) {
        return orderDao.searchWithUserAndPayment(orderCode, userName, fromDate, status);
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

    public List<Map<String, Object>> getAllOrdersWithUserName() {
        return orderDao.findAllWithUserName();
    }


    public double getRevenueTotal() {
        return orderDao.calculateRevenueTotal();
    }
}