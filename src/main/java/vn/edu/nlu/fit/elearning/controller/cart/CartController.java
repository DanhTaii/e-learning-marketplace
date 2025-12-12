package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.services.OrderItemService;
import vn.edu.nlu.fit.elearning.services.OrderService;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    private OrderItemService orderItemService;
    private OrderService orderService;

    public CartController() {
        this.orderItemService = new OrderItemService();
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");


        Order order = this.orderService.findOrderPending(userId);
        List<OrderItemDTO> list = this.orderItemService.getCartItems(order.getId());

        List<OrderItemDTO> itemSelected = this.orderItemService.getOrderItemSelected(order.getId());
        double totalAmonut = 0;
        double finalAmount = 0;
        for (OrderItemDTO items : itemSelected) {
            finalAmount += items.getPriceNew();
            totalAmonut += items.getPriceOld();
        }

        order.setTotalAmount(totalAmonut);
        order.setFinalAmount(finalAmount);
        request.setAttribute("order", order);
        request.setAttribute("list", list);
        request.setAttribute("orderItems", itemSelected);
        request.getRequestDispatcher("/html-personal-cart/cart.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// hành động của người dùng
        String action = request.getParameter("action");
// nguoi dung hanh dong tick va bo tick
        if ("updateSelected".equals(action)) {
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");
            Order order = this.orderService.findOrderPending(userId);
            // lay danh sach ID cua san pham dang duoc tick
            String[] selectedIds = request.getParameterValues("itemSelected");
            this.orderItemService.updateSelected(order.getId(), selectedIds);
        }
        doGet(request, response);
    }
}
