package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.services.OrderItemService;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderItemController", value = "/cart")
public class OrderItemController extends HttpServlet {
    private OrderItemService orderItemService;

    public OrderItemController() {
        this.orderItemService = new OrderItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");

        int userId = userIdObj;
        List<OrderItemDTO> list = this.orderItemService.getCartItems(userId);

        request.setAttribute("list", list);
        request.getRequestDispatcher("/html-personal-cart/cart.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
