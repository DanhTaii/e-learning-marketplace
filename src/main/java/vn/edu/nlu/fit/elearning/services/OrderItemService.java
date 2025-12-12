package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.OrderItem;

import java.util.List;

public class OrderItemService {

    private OrderItemDao oid;

    public OrderItemService() {
        this.oid = new OrderItemDao();
    }

    public List<OrderItemDTO> getCartItems(Integer userId) {
        List<OrderItemDTO> cartItems = oid.getCartItemsByUserId(userId);
        return cartItems;
    }

    public List<OrderItemDTO> getOrderItemSelected(Integer userId) {
        List<OrderItemDTO> itemSelected = oid.geOrderItemSelected(userId);
        return itemSelected;
    }

    // cập nhật tick
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

    public int createOrderItem(OrderItem orderItem) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<OrderItem> getAllOrderItems() {
        // TODO: Implement getAll logic
        return oid.findAll();
    }

    public OrderItem getOrderItemById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateOrderItem(OrderItem orderItem) {
        // TODO: Implement update logic
    }

    public void deleteOrderItem(int id) {
        // TODO: Implement delete logic
    }
}