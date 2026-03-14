package vn.edu.nlu.fit.elearning.feature.payment.service;

import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;

import java.util.List;

public interface PaymentService {
    int createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(int id);

    void updatePayment(Payment payment);

    void deletePayment(int id);
}
