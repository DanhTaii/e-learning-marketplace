package vn.edu.nlu.fit.elearning.feature.payment.dao;

import vn.edu.nlu.fit.elearning.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;

import java.util.List;

public interface PaymentDao extends BaseCrudDao<Payment, Integer> {
    @Override
    int create(Payment entity);

    @Override
    Payment findById(Integer id);

    @Override
    List<Payment> findAll();

    @Override
    int update(Payment entity);

    @Override
    int delete(Integer id);
}
