package vn.edu.nlu.fit.elearning.feature.payment_method.service;

import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDao;
import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;

import java.util.List;

public class PaymentMethodServiceImpl implements PaymentMethodService {

    private PaymentMethodDao pmd;

    public PaymentMethodServiceImpl() {
        this.pmd = new PaymentMethodDaoImpl();
    }

    @Override
    public int createPaymentMethod(PaymentMethod paymentMethod) {
          return   pmd.create(paymentMethod);


    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return pmd.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethodById(int id) {
        return pmd.findById(id);
    }

    @Override
    public int updatePaymentMethod(PaymentMethod paymentMethod) {
        return pmd.update(paymentMethod);
    }

    @Override
    public boolean deletePaymentMethod(int id) {
        int status = pmd.delete(id);
        return status > 0;
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethodsByName(String name) {
        return pmd.findByName(name);
    }
}
