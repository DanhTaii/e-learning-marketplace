package vn.edu.nlu.fit.elearning.feature.order_item.dao;

import vn.edu.nlu.fit.elearning.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;

import java.util.List;

public interface OrderItemDao extends BaseCrudDao<OrderItem, Integer> {
    List<OrderItem> getCartItemsByUserId(Integer orderId);

    List<OrderItem> geOrderItemSelected(Integer orderId);

    @Override
    int create(OrderItem entity);

    @Override
    OrderItem findById(Integer orderId);

    @Override
    List<OrderItem> findAll();

    void updateSelection(Integer orderItemId, boolean status);

    void unselectAll(Integer orderId);

    List<OrderItemDTO> getReceiptItems(int orderId);

    @Override
    int update(OrderItem entity);

    @Override
    int delete(Integer id);
}
