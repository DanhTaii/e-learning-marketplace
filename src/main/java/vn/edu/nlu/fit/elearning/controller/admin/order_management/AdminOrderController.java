package vn.edu.nlu.fit.elearning.controller.admin.order_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.services.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderController", value = "/admin/orders")
public class AdminOrderController extends HttpServlet {

    private OrderService orderService;

    public AdminOrderController() {
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> listOrders = orderService.getAllOrders();
        request.setAttribute("listOrders", listOrders);
        request.setAttribute("currentPage", "orders");

        request.getRequestDispatcher("/html-admin/order-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/admin/orders");
            return;
        }

        try {
            switch (action) {
                case "delete":
                    int orderId = Integer.parseInt(request.getParameter("id"));
                    boolean deleted = orderService.deleteOrder(orderId);
                    if (deleted) {
                        request.getSession().setAttribute("flashSuccess", "Xóa đơn hàng thành công!");
                    } else {
                        request.getSession().setAttribute("flashError", "Xóa đơn hàng thất bại!");
                    }
                    break;

                // Có thể mở rộng thêm: update status, search, v.v.
                default:
                    request.getSession().setAttribute("flashError", "Hành động không hợp lệ!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Có lỗi xảy ra khi xử lý đơn hàng!");
        }

        // Sau mọi hành động POST → redirect về danh sách để tránh resubmit
        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}