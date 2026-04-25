package vn.edu.nlu.fit.elearning.feature.payment.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ConfirmController", value = "/confirm-payment")
public class ConfirmController extends HttpServlet {
    private transient OrderService orderService;
    private transient PaymentService paymentService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.paymentService = BeanContainer.getBean(PaymentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức GET không được hỗ trợ cho endpoint này");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        CartService cartService = (CartService) session.getAttribute("cart");

        if (userId == null || cartService == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int paymentMethodId = Integer.parseInt(request.getParameter("payment-method-id"));
        Order order = orderService.createOrderPending(userId, cartService, paymentMethodId);

        if (paymentMethodId == 2) {

            String vnpayUrl = paymentService.generateVNPAYUrl(order, request);
            response.sendRedirect(vnpayUrl);
        } else {

            response.sendRedirect(request.getContextPath() + "/receipt?orderId=" + order.getId());
        }
    }
}