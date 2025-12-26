package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.model.OrderItem;
import vn.edu.nlu.fit.elearning.services.OrderItemService;
import vn.edu.nlu.fit.elearning.services.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {
    private OrderService orderService;
    private OrderItemService orderItemService;

    public PaymentController() {
        this.orderService = new OrderService();
        this.orderItemService = new OrderItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");

        int userId = userIdObj;

        Order order = orderService.findOrderPending(userId);
        List<OrderItem> orderItemsSelect = orderItemService.getOrderItemSelected(order.getId());

        double totalAmount = 0;
        double finalAmount = 0;
        double discountAmount = 0;

        for (OrderItem items : orderItemsSelect) {
//            finalAmount += items.getPriceNew();
//            totalAmount += items.getPriceOld();
//            discountAmount += items.getPriceOld() - items.getPriceNew();
        }
        order.setTotalAmount(totalAmount);
        order.setDiscountAmount(discountAmount);
        order.setFinalAmount(finalAmount);

        request.setAttribute("order", order);
        request.setAttribute("orderItems", orderItemsSelect);
        request.getRequestDispatcher("/html-personal-cart/payment.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
