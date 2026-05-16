package vn.edu.nlu.fit.elearning.feature.payment.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.payment.dto.PaymentSummaryDTO;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public interface PaymentService {
    int createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(int id);

    void updatePayment(Payment payment);

    void deletePayment(int id);

    String generateVNPAYUrl(Order order, HttpServletRequest request);

    PaymentSummaryDTO calculatePaymentSummary(CartService cart, Voucher voucher);
}
