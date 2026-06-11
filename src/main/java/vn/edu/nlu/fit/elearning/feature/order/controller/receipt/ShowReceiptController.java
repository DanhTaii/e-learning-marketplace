package vn.edu.nlu.fit.elearning.feature.order.controller.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowReceiptController", value = "/receipt")

public class ShowReceiptController extends HttpServlet {
OrderItemService orderItemService;
OrderService orderService;
PaymentMethodService paymentMethodService;
    @Override
    public void init() throws ServletException {
        super.init();
        this.orderItemService = BeanContainer.getBean(OrderItemService.class);
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        Integer orderId = (Integer) session.getAttribute("receipt_order_id");

        if (orderId == null) {
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }

        Order order = orderService.getOrderById(orderId);

        if (order == null || order.getUserId() != userId) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xem biên lai này.");
            return;
        }
        List<OrderItemDTO> orderItemList = orderItemService.getReceiptByOrderId(orderId);
        PaymentMethod pm = paymentMethodService.getPaymentMethodById(order.getPaymentMethodId());
        request.setAttribute("order",order);
        request.setAttribute("orderItemList",orderItemList);
        request.setAttribute("paymentMethod",pm);

        request.getRequestDispatcher("/views/pages/cart/receipt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam != null && !orderIdParam.trim().isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdParam);
                session.setAttribute("receipt_order_id", orderId);
            } catch (NumberFormatException e) {
            }
        }

        response.sendRedirect(request.getContextPath() + "/receipt");

    }
}
