package vn.edu.nlu.fit.elearning.feature.order_item.dao;

import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;

import java.util.List;

public interface OrderItemDao {
    List<OrderItem> getCartItemsByUserId(Integer orderId);

    List<OrderItem> geOrderItemSelected(Integer orderId);

    int create(OrderItem entity);

    OrderItem findById(Integer orderId);

    List<OrderItem> findAll();

    void updateSelection(Integer orderItemId, boolean status);

    void unselectAll(Integer orderId);

    List<OrderItemDTO> getReceiptItems(int orderId);

    int update(OrderItem entity);

    int delete(Integer id);
}
