package vn.edu.nlu.fit.elearning.feature.payment_method.service;

import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    int createPaymentMethod(PaymentMethod paymentMethod);

    List<PaymentMethod> getAllPaymentMethods();

    PaymentMethod getPaymentMethodById(int id);

    int updatePaymentMethod(PaymentMethod paymentMethod);

    boolean deletePaymentMethod(int id);

    List<PaymentMethod> getAllPaymentMethodsByName(String name);
}
