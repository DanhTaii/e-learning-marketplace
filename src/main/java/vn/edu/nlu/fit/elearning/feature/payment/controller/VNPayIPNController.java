package vn.edu.nlu.fit.elearning.feature.payment.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.payment_method.vnpay.VnpayConstants;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VNPayIPNController", value = "/vnpay-ipn")
public class VNPayIPNController extends HttpServlet {
    private transient OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            boolean isValidSignature = VnpayConstants.validateSignature(request);
            String responseCode = request.getParameter("vnp_ResponseCode");
            String orderCode = request.getParameter("vnp_TxnRef");
            String transactionNo = request.getParameter("vnp_TransactionNo");

            if (isValidSignature) {
                boolean isSuccess = "00".equals(responseCode);

                orderService.processPaymentResponse(orderCode, transactionNo, isSuccess);


                out.print("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}");
            } else {

                out.print("{\"RspCode\":\"97\",\"Message\":\"Invalid Checksum\"}");
            }
        } catch (Exception e) {

            out.print("{\"RspCode\":\"99\",\"Message\":\"Unknown error\"}");
        } finally {
            out.flush();
        }
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}