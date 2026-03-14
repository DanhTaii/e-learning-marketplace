package vn.edu.nlu.fit.elearning.feature.payment.dao;

import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;

import java.util.List;

public interface PaymentDao {
    int create(Payment entity);

    Payment findById(Integer id);

    List<Payment> findAll();

    int update(Payment entity);

    int delete(Integer id);
}
