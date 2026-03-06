package vn.edu.nlu.fit.elearning.feature.payment.service;

import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDao;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;

import java.util.List;

public class PaymentService {

    private PaymentDao pd;

    public PaymentService() {
        this.pd = new PaymentDao();
    }

    public int createPayment(Payment payment) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<Payment> getAllPayments() {
        // TODO: Implement getAll logic
        return pd.findAll();
    }

    public Payment getPaymentById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updatePayment(Payment payment) {
        // TODO: Implement update logic
    }

    public void deletePayment(int id) {
        // TODO: Implement delete logic
    }
}