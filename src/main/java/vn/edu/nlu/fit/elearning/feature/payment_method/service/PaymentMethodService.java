package vn.edu.nlu.fit.elearning.feature.payment_method.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.payment.PaymentMethodFilter;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethod> getAllPaymentMethods();

    PaymentMethod getPaymentMethodById(int id);

    int updatePaymentMethod(PaymentMethod paymentMethod);

    boolean deletePaymentMethod(int id);

    List<PaymentMethod> getAllPaymentMethodsByName(String name);

    List<PaymentMethod> getPaymentMethodsByFilter(PaymentMethodFilter filter);

    int getCountPaymentMethodsByFilter(PaymentMethodFilter filter);

    int getTotalPaymentMethods();
}
