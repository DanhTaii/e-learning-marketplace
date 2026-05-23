package vn.edu.nlu.fit.elearning.feature.payment.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.payment.dto.PaymentSummaryDTO;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentService;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {
    PaymentMethodService paymentMethodService;
private  transient PaymentService paymentService;
    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
        this.paymentService = BeanContainer.getBean(PaymentService.class);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        CartService ICartService = (CartService) session.getAttribute("cart");

        Integer userId = (Integer) session.getAttribute("userId");

        if (ICartService == null || ICartService.getSelectedQuantity() == 0) {
            response.sendRedirect(request.getContextPath() + "/personal/cart");
            return;
        }
        Voucher sessionVoucher = (Voucher) session.getAttribute("appliedVoucher");

        PaymentSummaryDTO summaryDTO = paymentService.calculatePaymentSummary(userId,ICartService, sessionVoucher);
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();

        if (sessionVoucher != null && summaryDTO.getAppliedVoucher() == null) {
            session.removeAttribute("appliedVoucher");
            session.removeAttribute("discountAmount");
        }

        request.setAttribute("summary", summaryDTO);
        request.setAttribute("paymentMethod", paymentMethods);
        request.getRequestDispatcher("views/pages/cart/payment.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}