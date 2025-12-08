package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.PaymentMethod;

import java.util.List;

public class PaymentMethodDao extends BaseDao implements BaseCrudDao<PaymentMethod, Integer> {

    @Override
    public void create(PaymentMethod entity) {
        // TODO: Implement create logic
    }

    @Override
    public PaymentMethod findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<PaymentMethod> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT id, name, code, icon_url, is_active\n" +
                    "FROM Payment_Methods\n" +
                    "ORDER BY id;\n").mapToBean(PaymentMethod.class).list();
        });
    }

    @Override
    public int update(PaymentMethod entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }
}