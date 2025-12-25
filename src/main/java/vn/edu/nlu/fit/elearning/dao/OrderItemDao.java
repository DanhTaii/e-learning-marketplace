package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.OrderItem;

import java.util.List;

public class OrderItemDao extends BaseDao implements BaseCrudDao<OrderItem, Integer> {


    public List<OrderItemDTO> getCartItemsByUserId(Integer orderId) {
        Jdbi jdbi = getJdbi();
        String sql = "SELECT oi.id AS id, c.id AS courseId, c.title, oi.is_selected AS selected, c.thumbnail_url, c.rating, c.level, c.price AS price_old, oi.price_at_purchase AS price_new, SUM(l.duration_minutes) AS durationHours , COUNT(l.id) AS total_lesson ,SUM(c.student_count) AS studentCount                   \n" +
                "FROM order_items oi\n" +
                "JOIN Orders o ON oi.order_id = o.id\n" +
                "JOIN Courses c ON oi.course_id = c.id\n" +
                "LEFT JOIN Lessons l ON c.id = l.course_id\n" +
                "WHERE oi.order_id = ?\n" +
                "GROUP BY oi.id, c.id, o.user_id, c.title, oi.is_selected, c.thumbnail_url,c.rating, c.level, c.price,oi.price_at_purchase\n";

        return jdbi.withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, orderId)
                    .mapToBean(OrderItemDTO.class).list();
        });
    }


    public List<OrderItemDTO> geOrderItemSelected(Integer orderId) {
        String sql = "SELECT c.thumbnail_url, c.title, c.price AS price_old , oi.price_at_purchase AS price_new \n" +
                "                FROM Order_Items AS oi  \n" +
                "                JOIN Courses AS c ON oi.course_id = c.id\n" +
                "                WHERE oi.order_id = ?  AND oi.is_selected = true";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, orderId)
                    .mapToBean(OrderItemDTO.class).list();


        });
    }


    @Override
    public int create(OrderItem entity) {
        // TODO: Implement create logic
        return 0;
    }

    @Override
    public OrderItem findById(Integer orderId) {
        return null;
    }

    @Override
    public List<OrderItem> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT oi.id AS order_item_id, oi.order_id, o.order_code, oi.course_id, c.title AS course_title, c.price AS price_old, (c.price - c.discount_price) AS price_new, oi.price_at_purchase\n" +
                    "FROM Order_Items oi\n" +
                    "JOIN Orders o ON oi.order_id = o.id\n" +
                    "JOIN Courses c ON oi.course_id = c.id\n" +
                    "ORDER BY oi.order_id;\n").mapToBean(OrderItem.class).list();
        });
    }

    public void updateSelection(Integer orderItemId, boolean status) {
        String sql = "UPDATE order_items SET is_selected = ? WHERE id = ?";
        getJdbi().useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind(0, status)
                    .bind(1, orderItemId)
                    .execute();

        });
    }

    public void unselectAll(Integer orderId) {
        String sql = "UPDATE order_items SET is_selected = false WHERE order_id = ?";
        getJdbi().useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind(0, orderId)
                    .execute();
        });
    }

    @Override
    public int update(OrderItem entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }
}