package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.model.OrderItem;
import vn.edu.nlu.fit.elearning.services.OrderItemService;
import vn.edu.nlu.fit.elearning.services.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {
    private OrderService orderService;
    private OrderItemService orderItemService;

    public PaymentController() {
        this.orderService = new OrderService();
        this.orderItemService= new OrderItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String idOrder = request.getParameter("id");
        int orderId = 5;

        Order order = orderService.getOrderById(orderId);
        List<OrderItem> orderItemList = orderItemService.getOrderItemList(orderId);
        request.setAttribute("order", order);
        request.setAttribute("orderItems",orderItemList);

        request.getRequestDispatcher("/html-personal-cart/payment.jsp").forward(request, response);
    }



@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
}
}
