package vn.edu.nlu.fit.elearning.feature.order.service;

import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderService {
    int createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);

    Order findOrderPending(Integer userId);

    List<Map<String, Object>> searchOrders(String orderCode, String userName, Timestamp fromDate, String status);

    int updateOrder(Order order);

    boolean deleteOrder(int orderId);

    List<Map<String, Object>> getAllOrdersWithUserName();

    double getRevenueTotal();

    List<OrderDTO> getOrderHistoryByUserId(int userId);
}
