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

@WebServlet(name = "VNPayReturnController", value = "/vnpay-return")
public class VNPayReturnController extends HttpServlet {
    private transient OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isValidSignature = VnpayConstants.validateSignature(request);
        String responseCode = request.getParameter("vnp_ResponseCode");
        String orderCode = request.getParameter("vnp_TxnRef");
        String transactionNo = request.getParameter("vnp_TransactionNo");
        HttpSession session = request.getSession();

        if (isValidSignature && "00".equals(responseCode)) {
            orderService.processPaymentResponse(orderCode, transactionNo, true);

            Order order = orderService.getOrderByCode(orderCode);

            CartService cartService = (CartService) session.getAttribute("cart");
            if (cartService != null) {
                cartService.removeSelected();
                session.setAttribute("cart", cartService);
            }

            session.setAttribute("flashSuccess", "Thanh toán thành công! Chúc bạn học tốt.");
            response.sendRedirect(request.getContextPath() + "/receipt?orderId=" + order.getId());
        } else {
            orderService.processPaymentResponse(orderCode, transactionNo, false);
            Order order = orderService.getOrderByCode(orderCode);
            session.setAttribute("flashError", "Giao dịch thất bại hoặc bạn đã hủy thanh toán.");
            response.sendRedirect(request.getContextPath() + "/receipt?orderId=" + order.getId());
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}