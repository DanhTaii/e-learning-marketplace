package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.OrderItem;

import java.util.List;

public class OrderItemDao extends BaseDao implements BaseCrudDao<OrderItem, Integer> {


    public List<OrderItemDTO> getCartItemsByUserId(int userId) {
        Jdbi jdbi = getJdbi();
        String sql = "SELECT oi.id AS id, c.id AS courseId, c.title, oi.is_selected AS selected, c.thumbnail_url, c.rating, c.level, c.price AS price_old, oi.price_at_purchase AS price_new, SUM(l.duration_minutes) AS durationHours , COUNT(l.id) AS total_lesson ,SUM(c.student_count) AS studentCount                   \n" +
                "FROM order_items oi\n" +
                "JOIN Orders o ON oi.order_id = o.id\n" +
                "JOIN Courses c ON oi.course_id = c.id\n" +
                "LEFT JOIN Lessons l ON c.id = l.course_id\n" +
                "WHERE o.user_id = ? AND o.status = 'pending'\n" +
                "GROUP BY oi.id, c.id, o.user_id, c.title, oi.is_selected, c.thumbnail_url,c.rating, c.level, c.price,oi.price_at_purchase\n";

        return jdbi.withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, userId)
                    .mapToBean(OrderItemDTO.class).list();
        });
    }



    public List<OrderItem> getOrderItemList(int orderId){
        String sql = "SELECT C.thumbnail_url, C.title, OI.price_at_purchase \n" +
                "FROM Order_Items AS OI \n" +
                "JOIN Courses AS C ON OI.course_id = C.id\n" +
                "WHERE OI.order_id = ?";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, orderId)
                    .mapToBean(OrderItem.class).list();


        });
    }




    @Override
    public void create(OrderItem entity) {
        // TODO: Implement create logic
    }

    @Override
    public OrderItem findById(Integer orderId) {return null;
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