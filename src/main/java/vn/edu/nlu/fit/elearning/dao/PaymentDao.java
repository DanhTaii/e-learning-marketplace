package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Payment;

import java.util.List;

public class PaymentDao extends BaseDao implements BaseCrudDao<Payment, Integer> {

    @Override
    public void create(Payment entity) {
        // TODO: Implement create logic
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
                    "FROM Payments p\n" +
                    "JOIN Orders o ON p.order_id = o.id\n" +
                    "JOIN Payment_Methods pm ON p.payment_method_id = pm.id\n" +
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