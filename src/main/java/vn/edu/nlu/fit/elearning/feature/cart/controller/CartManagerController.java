package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.cart.model.Cart;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistService;

import java.io.IOException;

@WebServlet(name = "CartManagerController", value = "/cart-manager")

public class CartManagerController extends HttpServlet {
    private WishlistService ws;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ws = new WishlistService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null && action != null) {
            switch (action) {
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    cart.deleteCourse(id);
                    break;

                case "moveToWishlist":
                    int courseId = Integer.parseInt(request.getParameter("id"));
                    ws.addCourseToWishlist(userId, courseId);
                    cart.deleteCourse(courseId);
                    break;

                case "moveSelectedToWishlist":
                    cart.getSelectedItems().forEach(item -> {
                        ws.addCourseToWishlist(userId, item.getCourse().getId());
                    });
                    cart.removeSelected();
                    break;

                case "removeSelected":
                    cart.removeSelected();
                    break;

                case "selectAll":
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    cart.selectAll(status);
            }
        }
        response.sendRedirect(request.getContextPath() + "/personal/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
