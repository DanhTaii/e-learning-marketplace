package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPaymentMethodController", value = "/admin/payment-methods")
public class AdminPaymentMethodController extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PaymentMethod> listPaymentMethods = paymentMethodService.getAllPaymentMethods();
        request.setAttribute("listPaymentMethods", listPaymentMethods);
        request.setAttribute("currentPage", "payment-methods");
        request.getRequestDispatcher("/views/pages/admin/payment/payment-method-management.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
