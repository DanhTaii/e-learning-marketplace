package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.PaymentMethodDao;
import vn.edu.nlu.fit.elearning.model.PaymentMethod;

import java.util.List;

public class PaymentMethodService {

    private PaymentMethodDao pmd;

    public PaymentMethodService() {
        this.pmd = new PaymentMethodDao();
    }

    public int createPaymentMethod(PaymentMethod paymentMethod) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        // TODO: Implement getAll logic
        return pmd.findAll();
    }

    public PaymentMethod getPaymentMethodById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        // TODO: Implement update logic
    }

    public void deletePaymentMethod(int id) {
        // TODO: Implement delete logic
    }
}