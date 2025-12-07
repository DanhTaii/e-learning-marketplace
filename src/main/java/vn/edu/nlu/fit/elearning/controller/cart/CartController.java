package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.CartItem;
import vn.edu.nlu.fit.elearning.services.CartService;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    private CartService cartService;

    public CartController() {
        this.cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");

        int userId = userIdObj;
        List<CartItem> list = this.cartService.getCartItems(userId);

        request.setAttribute("list", list);
        request.getRequestDispatcher("/html-personal-cart/cart.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
