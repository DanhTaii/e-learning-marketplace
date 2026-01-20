package vn.edu.nlu.fit.elearning.controller.cart.receipt_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.model.OrderItem;
import vn.edu.nlu.fit.elearning.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.services.OrderItemService;
import vn.edu.nlu.fit.elearning.services.OrderService;
import vn.edu.nlu.fit.elearning.services.PaymentMethodService;

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
        this.orderItemService = new OrderItemService();
        this.orderService = new OrderService();
        this.paymentMethodService = new PaymentMethodService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int orderId = Integer.parseInt(request.getParameter("orderId"));

        Order order = orderService.getOrderById(orderId);
        List<OrderItemDTO> orderItemList = orderItemService.getReceiptByOrderId(orderId);
        PaymentMethod pm = paymentMethodService.getPaymentMethodById(order.getPaymentMethodId());
        request.setAttribute("order",order);
        request.setAttribute("orderItemList",orderItemList);
        request.setAttribute("paymentMethod",pm);

        request.getRequestDispatcher("/html-personal-cart/receipt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
