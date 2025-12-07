package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dao.CartDao;
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
        // Lệnh in DEBUG: Kiểm tra xem Session có tồn tại không
        System.out.println("DEBUG (Cart): Session có tồn tại không? " + (session != null));

        // --- Lệnh in DEBUG CẦN THIẾT ---
        System.out.println("==================================================");
        System.out.println("DEBUG (Cart): Giá trị userId từ Session: " + userIdObj);
        System.out.println("==================================================");


        int userId = userIdObj;
        List<CartItem> list = this.cartService.getCartItems(userId);
        // ---> THÊM DÒNG NÀY <---
        System.out.println("DEBUG (Cart): Tìm thấy " + list.size() + " sản phẩm trong giỏ.");
        request.setAttribute("list", list);
        request.getRequestDispatcher("/html-personal-cart/cart.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
