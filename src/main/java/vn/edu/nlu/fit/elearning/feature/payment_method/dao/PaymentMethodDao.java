package vn.edu.nlu.fit.elearning.feature.payment_method.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.payment.PaymentMethodFilter;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodDao {
    PaymentMethod findById(Integer id);

    List<PaymentMethod> findByName(String name);

    List<PaymentMethod> findAll();

    int update(PaymentMethod entity);

    int delete(Integer id);

    List<PaymentMethod> findPaymentMethodsByFilter(PaymentMethodFilter filter);

    int countPaymentMethodsByFilter(PaymentMethodFilter filter);

    int countAllPaymentMethods();
}
