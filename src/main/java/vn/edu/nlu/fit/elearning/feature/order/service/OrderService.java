package vn.edu.nlu.fit.elearning.feature.order.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.order.OrderFilter;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderService {
    int createOrder(Order order);

    Order createOrderPending(Integer userId, CartService cart, int paymentMethodId , Voucher voucher);

    void processPaymentResponse(String orderCode, String transactionNo, boolean isSuccess);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);

    Order getOrderByCode(String orderCode);

    Order findOrderPending(Integer userId);

    List<Order> searchOrders(OrderFilter filter);


    int getCountOrdersByFilter(OrderFilter filter);

    int updateOrder(Order order);

    boolean deleteOrder(int orderId);

    List<Map<String, Object>> getAllOrdersWithUserName();

    double getRevenueTotal();

    List<OrderDTO> getOrderHistoryByUserId(int userId);

    int getTotalOrders();

    int countOrdersByTimeRange(String timeRange);

    double getRevenueTotalByTimeRange(String timeRange);

    double getTotalRevenueByDateRange(String fromDate, String toDate);

    void checkAndCancelExpiredOrders();
}
