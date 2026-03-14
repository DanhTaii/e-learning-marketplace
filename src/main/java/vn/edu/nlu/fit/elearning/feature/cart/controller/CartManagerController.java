package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.cart.service.ICart;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistService;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistServiceImpl;

import java.io.IOException;

@WebServlet(name = "CartManagerController", value = "/cart-manager")

public class CartManagerController extends HttpServlet {
    private WishlistService ws;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ws = new WishlistServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        ICart ICart = (ICart) session.getAttribute("cart");

        if (ICart != null && action != null) {
            switch (action) {
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    ICart.deleteCourse(id);
                    break;

                case "moveToWishlist":
                    int courseId = Integer.parseInt(request.getParameter("id"));
                    ws.addCourseToWishlist(userId, courseId);
                    ICart.deleteCourse(courseId);
                    break;

                case "moveSelectedToWishlist":
                    ICart.getSelectedItems().forEach(item -> {
                        ws.addCourseToWishlist(userId, item.getCourse().getId());
                    });
                    ICart.removeSelected();
                    break;

                case "removeSelected":
                    ICart.removeSelected();
                    break;

                case "selectAll":
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    ICart.selectAll(status);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = String.format("{\"selectedQuantity\": %d, \"finalPriceTotal\": %.0f, \"total\": %.0f}",
                    ICart.getSelectedQuantity(), ICart.getFinalPriceTotal(), ICart.getTotal());
            response.getWriter().write(json);
            return ;
        }
        response.sendRedirect(request.getContextPath() + "/personal/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
