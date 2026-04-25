package vn.edu.nlu.fit.elearning.feature.order.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailController", value = "/admin/order/detail")
public class OrderDetailController extends BaseController {

    private transient OrderService orderService;
    private transient OrderItemService orderItemService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.orderItemService = BeanContainer.getBean(OrderItemService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {
                int orderId = RequestUtils.getParameterAsInt(request, "id", -1);
                Order order = orderService.getOrderById(orderId);

                if (order != null) {
                    List<OrderItemDTO> orderItems = orderItemService.getReceiptByOrderId(orderId);
                    request.setAttribute("order", order);
                    request.setAttribute("orderItems", orderItems);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng!");
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu ID đơn hàng!");
                return;
            }

            // Sử dụng this.forward từ BaseController thay vì RequestDispatcher mặc định
            this.forward(request, response, "/views/pages/admin/order/order-create.jsp");

        } catch (Exception e) {
            log("Unexpected error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = RequestUtils.getParameterAsInt(request, "id", -1);

        if (id <= 0) {
            handleError(request, response, "ID đơn hàng không hợp lệ!");
            return;
        }

        try {
            Order order = orderService.getOrderById(id);
            if (order == null) {
                handleError(request, response, "Đơn hàng không tồn tại!");
                return;
            }


            request.getSession().setAttribute("flashSuccess", "Tính năng cập nhật đang được hoàn thiện!");
            response.sendRedirect(request.getContextPath() + "/admin/orders");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());

            try {
                List<OrderItemDTO> orderItems = orderItemService.getReceiptByOrderId(id);
                request.setAttribute("orderItems", orderItems);
            } catch (Exception ex) {
                log("Error reloading order items", ex);
            }

            this.forward(request, response, "/views/pages/admin/order/order-create.jsp");
        }
    }
}