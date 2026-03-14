package vn.edu.nlu.fit.elearning.feature.payment_method.dao;

import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodDao {
    int create(PaymentMethod entity);

    PaymentMethod findById(Integer id);

    List<PaymentMethod> findByName(String name);

    List<PaymentMethod> findAll();

    int update(PaymentMethod entity);

    int delete(Integer id);
}
