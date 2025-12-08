package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Order;

import java.util.List;

public class OrderDao extends BaseDao implements BaseCrudDao<Order, Integer> {

    @Override
    public void create(Order entity) {
        // TODO: Implement create logic
    }

    @Override
    public Order findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<Order> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT o.id AS order_id, o.order_code, o.user_id, u.first_name, u.last_name, u.email, oi.course_id, c.title AS course_title, c.price AS price_old, (c.price - c.discount_price) AS price_new, o.total_amount, o.discount_amount, o.final_amount, o.status, o.paid_at, o.created_at\n" +
                    "FROM Orders o\n" +
                    "JOIN Users u ON o.user_id = u.id\n" +
                    "JOIN Order_Items oi ON o.id = oi.order_id\n" +
                    "JOIN Courses c ON oi.course_id = c.id\n" +
                    "ORDER BY o.created_at DESC;\n").mapToBean(Order.class).list();
        });
    }

    @Override
    public int update(Order entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }



}