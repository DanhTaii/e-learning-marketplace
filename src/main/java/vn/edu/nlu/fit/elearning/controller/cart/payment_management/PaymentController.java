package vn.edu.nlu.fit.elearning.controller.cart.payment_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.services.PaymentMethodService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentController", value = "/show-payment")
public class PaymentController extends HttpServlet {
    PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = new PaymentMethodService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
           request.setAttribute("paymentMethod",paymentMethods);
        request.getRequestDispatcher("/html-personal-cart/payment.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}