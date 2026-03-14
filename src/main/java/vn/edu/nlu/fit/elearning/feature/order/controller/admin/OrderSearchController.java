package vn.edu.nlu.fit.elearning.feature.order.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderServiceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderSearchController", value = "/admin/orders/search")
public class OrderSearchController extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderCode = req.getParameter("orderCode");
        String userName = req.getParameter("userName");
        String status = req.getParameter("status");

        // Chuyển fromDate từ input type="datetime-local"
        String fromDateStr = req.getParameter("fromDate"); // dạng yyyy-MM-ddTHH:mm
        Timestamp fromDate = null;
        if (fromDateStr != null && !fromDateStr.isEmpty()) {
            fromDate = Timestamp.valueOf(fromDateStr.replace('T', ' ') + ":00");
        }

        List<Map<String, Object>> listOrders = orderService.searchOrders(orderCode, userName, fromDate, status);
        req.setAttribute("listOrders", listOrders);
        req.setAttribute("currentPage", "orders");
        req.getRequestDispatcher("/views/pages/admin/order/order-management.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}