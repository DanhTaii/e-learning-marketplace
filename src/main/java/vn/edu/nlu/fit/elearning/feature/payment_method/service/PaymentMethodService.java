package vn.edu.nlu.fit.elearning.feature.payment_method.service;

import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDao;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public class PaymentMethodService {

    private PaymentMethodDao pmd;

    public PaymentMethodService() {
        this.pmd = new PaymentMethodDao();
    }

    public int createPaymentMethod(PaymentMethod paymentMethod) {
          return   pmd.create(paymentMethod);


    }

    public List<PaymentMethod> getAllPaymentMethods() {
        return pmd.findAll();
    }

    public PaymentMethod getPaymentMethodById(int id) {
        return pmd.findById(id);
    }

    public int updatePaymentMethod(PaymentMethod paymentMethod) {
        return pmd.update(paymentMethod);
    }

    public boolean deletePaymentMethod(int id) {
        int status = pmd.delete(id);
        return status > 0;
    }

    public List<PaymentMethod> getAllPaymentMethodsByName(String name) {
        return pmd.findByName(name);
    }
}
