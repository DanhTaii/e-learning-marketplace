package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.model.OrderItem;

import java.util.List;

public class OrderDao extends BaseDao implements BaseCrudDao<Order, Integer> {

    @Override
    public int create(Order entity) {
        String sql = "INSERT INTO Orders (order_code, user_id, payment_method_id, total_amount, discount_amount, final_amount, status)\n" +
                "VALUES (:orderCode, :userId, :paymentMethodId, :totalAmount, :discountAmount, :finalAmount, :status)";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public Order findById(Integer orderId) {
        String sql = "SELECT o.*, pm.name AS payment_method_name, u.first_name, u.last_name, u.email AS user_email\n" +
                "FROM Orders o\n" +
                "LEFT JOIN Payment_Methods pm ON o.payment_method_id = pm.id\n" +
                "LEFT JOIN Users u ON o.user_id = u.id\n" +
                "WHERE o.id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind("id", orderId)
                    .mapToBean(Order.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    public Order findOrderPending(Integer userId) {
        String sql = "SELECT o.*, pm.name AS payment_method_name, u.first_name, u.last_name, u.email AS user_email\n" +
                "FROM Orders o\n" +
                "LEFT JOIN Payment_Methods pm ON o.payment_method_id = pm.id\n" +
                "LEFT JOIN Users u ON o.user_id = u.id\n" +
                "WHERE o.user_id = :userId AND o.status = 'PENDING'\n" +
                "ORDER BY o.created_at DESC\n" +
                "LIMIT 1";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind("userId", userId)
                    .mapToBean(Order.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT o.id, o.order_code AS orderCode, " +
                "o.user_id AS userId, " +
                "o.payment_method_id AS paymentMethodId, " +
                "o.total_amount AS totalAmount, " +
                "o.discount_amount AS discountAmount, " +
                "o.final_amount AS finalAmount, " +
                "o.status, " +
                "o.paid_at AS paidAt, " +
                "o.created_at AS createdAt, " +
                "o.updated_at AS updatedAt " +
                "FROM Orders o " +
                "ORDER BY o.created_at DESC";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapToBean(Order.class)
                    .list();
        });
    }


    @Override
    public int update(Order entity) {
        String sql = "UPDATE Orders\n" +
                "SET order_code = :orderCode,\n" +
                "    user_id = :userId,\n" +
                "    payment_method_id = :paymentMethodId,\n" +
                "    total_amount = :totalAmount,\n" +
                "    discount_amount = :discountAmount,\n" +
                "    final_amount = :finalAmount,\n" +
                "    status = :status,\n" +
                "    paid_at = :paidAt\n" +
                "WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Orders WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", id)
                    .execute();
        });
    }

    public double calculateRevenueTotal() {
        String sql = "SELECT COALESCE(SUM(final_amount), 0)\n" +
                "FROM Orders\n" +
                "WHERE status = 'PAID' AND DATE(created_at) = CURDATE()";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapTo(Double.class)
                    .findFirst()
                    .orElse(0.0);
        });
    }
}