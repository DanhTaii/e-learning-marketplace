package vn.edu.nlu.fit.elearning.feature.order_item.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {


    @Override
    public List<OrderItem> getCartItemsByUserId(Integer orderId) {
        Jdbi jdbi = getJdbi();
        String sql = "SELECT oi.id AS id, c.id AS courseId, c.title, c.thumbnail_url, c.level, c.price AS price_old, oi.price_at_purchase AS price_new, SUM(l.duration_minutes) AS durationHours , COUNT(l.id) AS total_lesson                  \n" +
                "FROM order_items oi\n" +
                "JOIN orders o ON oi.order_id = o.id\n" +
                "JOIN courses c ON oi.course_id = c.id\n" +
                "LEFT JOIN lessons l ON c.id = l.course_id\n" +
                "WHERE oi.order_id = ?\n" +
                "GROUP BY oi.id, c.id, o.user_id, c.title,  c.thumbnail_url, c.level, c.price,oi.price_at_purchase\n";

        return jdbi.withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, orderId)
                    .mapToBean(OrderItem.class).list();
        });
    }


    @Override
    public List<OrderItem> geOrderItemSelected(Integer orderId) {
        String sql = "SELECT c.thumbnail_url, c.title, c.price AS price_old , oi.price_at_purchase AS price_new \n" +
                "                FROM order_items AS oi  \n" +
                "                JOIN courses AS c ON oi.course_id = c.id\n" +
                "                WHERE oi.order_id = ?  AND oi.is_selected = true";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, orderId)
                    .mapToBean(OrderItem.class).list();


        });
    }


    @Override
    public int create(OrderItem entity) {
        String sql = "INSERT INTO order_items (order_id, course_id, price_at_purchase) " +
                "VALUES (:orderId, :courseId, :priceAtPurchase)";
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(entity)
                        .execute()
        );
    }

    @Override
    public OrderItem findById(Integer orderId) {
        return null;
    }

    @Override
    public List<OrderItem> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT oi.id AS order_item_id, oi.order_id, o.order_code, oi.course_id, c.title AS course_title, c.price AS price_old, (c.price - c.discount_price) AS price_new, oi.price_at_purchase\n" +
                    "FROM order_items oi\n" +
                    "JOIN orders o ON oi.order_id = o.id\n" +
                    "JOIN courses c ON oi.course_id = c.id\n" +
                    "ORDER BY oi.order_id;\n").mapToBean(OrderItem.class).list();
        });
    }

    @Override
    public void updateSelection(Integer orderItemId, boolean status) {
        String sql = "UPDATE order_items SET is_selected = ? WHERE id = ?";
        getJdbi().useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind(0, status)
                    .bind(1, orderItemId)
                    .execute();

        });
    }

    @Override
    public void unselectAll(Integer orderId) {
        String sql = "UPDATE order_items SET is_selected = false WHERE order_id = ?";
        getJdbi().useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind(0, orderId)
                    .execute();
        });
    }
    @Override
    public List<OrderItemDTO> getReceiptItems(int orderId) {
        String sql = "SELECT oi.id, oi.order_id, oi.course_id, oi.price_at_purchase, " +
                "c.title AS courseTitle, c.thumbnail_url AS thumbnailUrl " +
                "FROM order_items oi " +
                "JOIN courses c ON oi.course_id = c.id " +
                "WHERE oi.order_id = ?";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind(0, orderId)
                        .mapToBean(OrderItemDTO.class)
                        .list()
        );
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