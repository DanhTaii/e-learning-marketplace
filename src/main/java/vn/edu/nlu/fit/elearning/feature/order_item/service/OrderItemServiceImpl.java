package vn.edu.nlu.fit.elearning.feature.order_item.service;

import vn.edu.nlu.fit.elearning.feature.order_item.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.feature.order_item.dao.OrderItemDaoImpl;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao oid;

    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.oid = orderItemDao;
    }

    @Override
    public List<OrderItem> getCartItems(Integer userId) {
        List<OrderItem> cartItems = oid.getCartItemsByUserId(userId);
        return cartItems;
    }

    @Override
    public List<OrderItem> getOrderItemSelected(Integer userId) {
        List<OrderItem> itemSelected = oid.geOrderItemSelected(userId);
        return itemSelected;
    }

    // cập nhật tick
    @Override
    public void updateSelected(Integer orderItemId, String[] selectedItemId) {
// bước 1 bỏ hết tick
        oid.unselectAll(orderItemId);
// bước 2 lập qua tat ca order-item được tick
        if (selectedItemId != null) {
            for (String item : selectedItemId) {
                int itemId = Integer.parseInt(item);
                oid.updateSelection(itemId, true);
            }

        }
    }

    @Override
    public int createOrderItem(OrderItem orderItem) {
        return oid.create(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        // TODO: Implement getAll logic
        return oid.findAll();
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        // TODO: Implement update logic
    }

    @Override
    public void deleteOrderItem(int id) {
        // TODO: Implement delete logic
    }
    @Override
    public List<OrderItemDTO> getReceiptByOrderId(int orderId){
       List<OrderItemDTO> receipt = oid.getReceiptItems(orderId);
       return  receipt;
    }
}