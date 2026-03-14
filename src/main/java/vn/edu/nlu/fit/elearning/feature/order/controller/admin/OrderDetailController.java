package vn.edu.nlu.fit.elearning.feature.order.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemServiceImpl;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetail", value = "/admin/order/detail")
public class OrderDetailController extends HttpServlet {

    private OrderService orderService;
    private OrderItemService orderItemService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.orderService = new OrderServiceImpl();
        this.orderItemService = new OrderItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            request.getSession().setAttribute("flashError", "Không tìm thấy ID đơn hàng!");
            response.sendRedirect(request.getContextPath() + "/admin/orders");
            return;
        }

        try {
            int orderId = Integer.parseInt(idParam);

            Order order = orderService.getOrderById(orderId);

            if (order == null) {
                request.getSession().setAttribute("flashError", "Đơn hàng không tồn tại!");
                response.sendRedirect(request.getContextPath() + "/admin/orders");
                return;
            }

            List<OrderItemDTO> orderItems = orderItemService.getReceiptByOrderId(orderId);

            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);

            request.getRequestDispatcher("/views/pages/admin/order/order-create.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "ID đơn hàng không hợp lệ!");
            response.sendRedirect(request.getContextPath() + "/admin/orders");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}