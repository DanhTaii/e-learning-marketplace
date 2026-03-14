package vn.edu.nlu.fit.elearning.feature.order_item.service;

import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getCartItems(Integer userId);

    List<OrderItem> getOrderItemSelected(Integer userId);

    // cập nhật tick
    void updateSelected(Integer orderItemId, String[] selectedItemId);

    int createOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItems();

    OrderItem getOrderItemById(int id);

    void updateOrderItem(OrderItem orderItem);

    void deleteOrderItem(int id);

    List<OrderItemDTO> getReceiptByOrderId(int orderId);
}
