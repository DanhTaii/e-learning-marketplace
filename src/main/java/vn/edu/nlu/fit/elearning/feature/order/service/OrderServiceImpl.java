package vn.edu.nlu.fit.elearning.feature.order.service;

import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDao;
import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDaoImpl;
import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;

import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public int createOrder(Order order) {
       return  orderDao.create(order);

    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public Order findOrderPending(Integer userId) {
        if (userId == null) {
            return null;
        }
        return orderDao.findOrderPending(userId);
    }

    @Override
    public List<Map<String, Object>> searchOrders(String orderCode, String userName, Timestamp fromDate, String status) {
        return orderDao.searchWithUserAndPayment(orderCode, userName, fromDate, status);
    }

    @Override
    public int updateOrder(Order order) {
        if (order != null) {
            return orderDao.update(order);
        }
        return 0;
    }


    @Override
    public boolean deleteOrder(int orderId) {
        int status = orderDao.delete(orderId);
        return status > 0;
    }

    @Override
    public List<Map<String, Object>> getAllOrdersWithUserName() {
        return orderDao.findAllWithUserName();
    }


    @Override
    public double getRevenueTotal() {
        return orderDao.calculateRevenueTotal();
    }
    @Override
    public List<OrderDTO> getOrderHistoryByUserId(int userId){
        List<OrderDTO> list = orderDao.getOrderHistoryByUserId(userId);
        return list;
    }
}