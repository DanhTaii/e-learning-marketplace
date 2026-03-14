package vn.edu.nlu.fit.elearning.feature.order.dao;

import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    int create(Order entity);

    Order findById(Integer orderId);

    Order findOrderPending(Integer userId);

    List<Order> findAll();

    int update(Order entity);

    int delete(Integer id);

    double calculateRevenueTotal();

    List<Order> getOrderBySearch(String orderCode, String userName, String fromDate, String status);

    List<Map<String, Object>> findAllWithUserName();

    List<Map<String, Object>> searchWithUserAndPayment(String orderCode, String userName, Timestamp fromDate, String status);

    List<OrderDTO> getOrderHistoryByUserId(int userId);
}
