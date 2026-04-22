package vn.edu.nlu.fit.elearning.feature.payment.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;

import java.util.List;

public class PaymentDaoImpl extends BaseDao implements PaymentDao {

    @Override
    public int create(Payment entity) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                INSERT INTO payments (order_id, payment_method_id, gateway_transaction_id, amount, status, created_at, update_at)
                VALUES (:orderId, :paymentMethodId, :gateway_transaction_id, :amount, :status, :createdAt, :updateAt)
            """)
                        .bindBean(entity)
                        .bind("status", entity.getStatus().name())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }


    @Override
    public Payment findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT p.id AS payment_id, p.order_id, o.order_code, p.payment_method_id, pm.name AS payment_method_name, pm.code AS payment_method_code, p.amount, p.transaction_id, p.status, p.created_at\n" +
                    "FROM payments p\n" +
                    "JOIN orders o ON p.order_id = o.id\n" +
                    "JOIN payment_methods pm ON p.payment_method_id = pm.id\n" +
                    "ORDER BY p.created_at DESC;\n").mapToBean(Payment.class).list();
        });
    }

    @Override
    public int update(Payment entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }
}