package vn.edu.nlu.fit.elearning.feature.payment.service;

import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDao;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao pd;

    public PaymentServiceImpl() {
        this.pd = new PaymentDaoImpl();
    }

    @Override
    public int createPayment(Payment payment) {
        // TODO: Implement creation logic
        return 0;
    }

    @Override
    public List<Payment> getAllPayments() {
        // TODO: Implement getAll logic
        return pd.findAll();
    }

    @Override
    public Payment getPaymentById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    @Override
    public void updatePayment(Payment payment) {
        // TODO: Implement update logic
    }

    @Override
    public void deletePayment(int id) {
        // TODO: Implement delete logic
    }
}