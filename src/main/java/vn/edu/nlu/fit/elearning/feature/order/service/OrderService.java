package vn.edu.nlu.fit.elearning.feature.order.service;

import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderService {
    int createOrder(Order order);

    Order createOrderPending(Integer userId, CartService cart, int paymentMethodId);

    void processPaymentResponse(String orderCode, String transactionNo, boolean isSuccess);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);

    Order getOrderByCode(String orderCode);

    Order findOrderPending(Integer userId);

    List<Map<String, Object>> searchOrders(String orderCode, String userName, Timestamp fromDate, String status);

    int updateOrder(Order order);

    boolean deleteOrder(int orderId);

    List<Map<String, Object>> getAllOrdersWithUserName();

    double getRevenueTotal();

    List<OrderDTO> getOrderHistoryByUserId(int userId);
}
