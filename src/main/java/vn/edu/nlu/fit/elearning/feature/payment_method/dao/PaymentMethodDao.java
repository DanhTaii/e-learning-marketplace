package vn.edu.nlu.fit.elearning.feature.payment_method.dao;

import vn.edu.nlu.fit.elearning.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodDao extends BaseCrudDao<PaymentMethod, Integer> {
    @Override
    int create(PaymentMethod entity);

    @Override
    PaymentMethod findById(Integer id);

    List<PaymentMethod> findByName(String name);

    @Override
    List<PaymentMethod> findAll();

    @Override
    int update(PaymentMethod entity);

    @Override
    int delete(Integer id);
}
