package vn.edu.nlu.fit.elearning.controller.admin.order_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.services.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminOrderController", value = "/admin/orders")
public class AdminOrderController extends HttpServlet {

    private OrderService orderService;

    public AdminOrderController() {
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Map<String, Object>> listOrders = orderService.getAllOrdersWithUserName();
        request.setAttribute("listOrders", listOrders);
        request.setAttribute("currentPage", "orders");

        request.getRequestDispatcher("/html-admin/order-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}