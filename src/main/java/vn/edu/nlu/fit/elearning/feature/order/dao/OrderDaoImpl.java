package vn.edu.nlu.fit.elearning.feature.order.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.order.OrderFilter;
import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int create(Order entity) {
        String sql = "INSERT INTO orders (order_code, user_id, username_snapshot, payment_method_id, total_amount, discount_amount, final_amount, status)\n" +
                "VALUES (:orderCode, :userId,:usernameSnapshot, :paymentMethodId, :totalAmount, :discountAmount, :finalAmount, :status)";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public Order findById(Integer orderId) {
        String sql = "SELECT o.id, o.order_code, o.user_id, o.payment_method_id, " +
                "o.total_amount, o.discount_amount, o.final_amount, o.status, " +
                "o.paid_at, o.created_at, o.updated_at,  o.username_snapshot " +
                "FROM orders o " +
                "WHERE o.id = :id";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", orderId)
                        .mapToBean(Order.class)
                        .findFirst()
                        .orElse(null)
        );
    }
    public Order findByCode(String orderCode) {
        String sql = "SELECT o.id, o.order_code, o.user_id, o.payment_method_id, " +
                "o.total_amount, o.discount_amount, o.final_amount, o.status, " +
                "o.paid_at, o.created_at, o.updated_at " +
                "FROM orders o " +
                "WHERE o.order_code = :orderCode";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("orderCode", orderCode)
                        .mapToBean(Order.class)
                        .findFirst()
                        .orElse(null)
        );
    }


    @Override
    public Order findOrderPending(Integer userId) {
        String sql = "SELECT o.*, pm.name AS payment_method_name, u.first_name, u.last_name, u.email AS user_email\n" +
                "FROM orders o\n" +
                "LEFT JOIN payment_methods pm ON o.payment_method_id = pm.id\n" +
                "LEFT JOIN users u ON o.user_id = u.id\n" +
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
                "o.updated_at AS updatedAt, " +
                "u.username AS username " +  // <-- Thêm cột username từ Users
                "FROM orders o " +
                "LEFT JOIN users u ON o.user_id = u.id " +
                "ORDER BY o.created_at DESC";
        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Order.class)
                        .list()
        );
    }


    @Override
    public int update(Order entity) {
        String sql = "UPDATE orders\n" +
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
        String sql = "DELETE FROM orders WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", id)
                    .execute();
        });
    }

    @Override
    public double calculateRevenueTotal() {
        String sql = "SELECT COALESCE(SUM(final_amount), 0)\n" +
                "FROM orders\n" +
                "WHERE status = 'PAID' AND DATE(created_at) = CURDATE()";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapTo(Double.class)
                    .findFirst()
                    .orElse(0.0);
        });
    }

    @Override
    public List<Order> getOrderBySearch(OrderFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildOrderWhereClause(filter, params);
        String sql = "SELECT o.id, o.order_code, o.user_id, o.payment_method_id, " +
                "o.total_amount, o.discount_amount, o.final_amount, o.status, " +
                "o.paid_at, o.created_at, o.updated_at, o.username_snapshot " +
                "FROM orders o "
                + whereClause
                + " ORDER BY o.created_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);

            params.forEach(query::bind);

            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());

            return query.mapToBean(Order.class).list();
        });
    }
    private String buildOrderWhereClause(OrderFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            where.append(" AND o.username_snapshot LIKE :nameSearch");
            params.put("nameSearch", "%" + filter.getName().trim() + "%");
        }
        if (filter.getCode() != null && !filter.getCode().trim().isEmpty()) {
            where.append(" AND o.order_code LIKE :code");
            params.put("nameSearch", "%" + filter.getCode().trim() + "%");
        }

        if (filter.getCourseId() > 0) {
            where.append(" AND EXISTS (SELECT 1 FROM order_items oi WHERE oi.order_id = o.id AND oi.course_id = :courseIdSearch)");
            params.put("courseIdSearch", filter.getCourseId());
        }

        if (filter.getFromDate() != null) {
            where.append(" AND o.created_at >= :fromDate");
            params.put("fromDate", filter.getFromDate());
        }

        if (filter.getToDate() != null) {
            where.append(" AND o.created_at <= :toDate");
            params.put("toDate", filter.getToDate());
        }

        if (filter.getStatus() != null) {
            where.append(" AND o.status = :status");
            params.put("status", filter.getStatus());
        }

        return where.toString();
    }

    @Override
    public List<Map<String, Object>> findAllWithUserName() {
        String sql = "SELECT o.id, o.order_code, o.user_id, o.payment_method_id, " +
                "o.total_amount, o.discount_amount, o.final_amount, o.status, " +
                "o.paid_at, o.created_at, o.updated_at, o.username_snapshot " +
                "FROM orders o " +
                "ORDER BY o.created_at DESC";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .map((rs, ctx) -> {
                            Map<String, Object> row = new HashMap<>();
                            Order order = new Order();
                            order.setId(rs.getInt("id"));
                            order.setOrderCode(rs.getString("order_code"));
                            order.setUserId(rs.getInt("user_id"));
                            order.setPaymentMethodId(rs.getInt("payment_method_id"));
                            order.setTotalAmount(rs.getInt("total_amount"));
                            order.setDiscountAmount(rs.getInt("discount_amount"));
                            order.setFinalAmount(rs.getInt("final_amount"));
                            order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                            order.setPaidAt(rs.getTimestamp("paid_at"));
                            order.setCreatedAt(rs.getTimestamp("created_at"));
                            order.setUpdatedAt(rs.getTimestamp("updated_at"));
                              order.setUsernameSnapshot(rs.getString("username_snapshot"));
                            row.put("order", order);
                            return row;
                        })
                        .list()
        );
    }

    @Override
    public List<Map<String, Object>> searchWithUserAndPayment(String orderCode, String userName, Timestamp fromDate, String status) {
        String sql = "SELECT o.id, o.order_code, o.user_id, o.payment_method_id, " +
                "o.total_amount, o.discount_amount, o.final_amount, o.status, " +
                "o.paid_at, o.created_at, o.updated_at, " +
                "u.username AS userName, pm.name AS paymentName " +
                "FROM orders o " +
                "LEFT JOIN users u ON o.user_id = u.id " +
                "LEFT JOIN payment_methods pm ON o.payment_method_id = pm.id " +
                "WHERE 1=1 " +
                (orderCode != null && !orderCode.isEmpty() ? " AND o.order_code LIKE :orderCode " : "") +
                (userName != null && !userName.isEmpty() ? " AND u.username LIKE :userName " : "") +
                (fromDate != null ? " AND o.created_at >= :fromDate " : "") +
                (status != null && !status.isEmpty() ? " AND o.status = :status " : "") +
                "ORDER BY o.created_at DESC";

        return getJdbi().withHandle(h -> {
            var q = h.createQuery(sql);
            if (orderCode != null && !orderCode.isEmpty()) q.bind("orderCode", "%" + orderCode + "%");
            if (userName != null && !userName.isEmpty()) q.bind("userName", "%" + userName + "%");
            if (fromDate != null) q.bind("fromDate", fromDate);
            if (status != null && !status.isEmpty()) q.bind("status", status);

            return q.map((rs, ctx) -> {
                Map<String, Object> row = new HashMap<>();
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("order_code"));
                order.setUserId(rs.getInt("user_id"));
                order.setPaymentMethodId(rs.getInt("payment_method_id"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setDiscountAmount(rs.getInt("discount_amount"));
                order.setFinalAmount(rs.getInt("final_amount"));
                order.setStatus(OrderStatus.valueOf(rs.getString("status")));
                order.setPaidAt(rs.getTimestamp("paid_at"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));
                row.put("order", order);
                row.put("userName", rs.getString("userName"));
                row.put("paymentName", rs.getString("paymentName"));
                return row;
            }).list();
        });
    }
    @Override
    public List<OrderDTO> getOrderHistoryByUserId(int userId) {
        String sql = "SELECT o.id, o.order_code, o.total_amount, o.discount_amount, o.final_amount, " +
                "o.status, o.created_at, pm.name AS paymentMethodName " +
                "FROM orders o " +
                "JOIN payment_methods pm ON o.payment_method_id = pm.id " +
                "WHERE o.user_id = :userId " +
                "ORDER BY o.created_at DESC";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(OrderDTO.class)
                        .list()
        );
    }

}