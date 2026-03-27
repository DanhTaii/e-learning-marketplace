package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "CartManagerController", value = "/cart-manager")

public class CartManagerController extends HttpServlet {
    private WishlistService ws;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ws = BeanContainer.getBean(WishlistService.class);
    }
    private void sendJsonResponse(HttpServletResponse response, CartService cartService) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = String.format("{\"totalQuantity\": %d, \"selectedQuantity\": %d, \"finalPriceTotal\":\"%s\", \"total\": \"%s\"}",
                cartService.getTotalQuantity(),
                cartService.getSelectedQuantity(),
                cartService.getFormatedFinalPriceTotal(),
                cartService.getFormatedTotal());
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        CartService ICartService = (CartService) session.getAttribute("cart");

        if (ICartService != null && action != null) {
            switch (action) {
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    ICartService.deleteCourse(id);
                    break;

                case "moveToWishlist":
                    int courseId = Integer.parseInt(request.getParameter("id"));
                    ws.addCourseToWishlist(userId, courseId);
                    ICartService.deleteCourse(courseId);
                    break;

                case "moveSelectedToWishlist":
                    ICartService.getSelectedItems().forEach(item -> {
                        ws.addCourseToWishlist(userId, item.getCourse().getId());
                    });
                    ICartService.removeSelected();
                    break;

                case "removeSelected":
                    ICartService.removeSelected();
                    break;

                case "selectAll":
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    ICartService.selectAll(status);
                    break;
            }
            sendJsonResponse(response, ICartService);
            return ;
        }
        response.sendRedirect(request.getContextPath() + "/personal/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartService ICartService = (CartService) session.getAttribute("cart");

        if (ICartService != null) {
            String[] selectedIds = request.getParameterValues("itemSelected");
            List<String> listId = (selectedIds != null) ? Arrays.asList(selectedIds) : new ArrayList<>();
            for (CartItem item : ICartService.getList()) {
                String currentId = String.valueOf(item.getCourse().getId());

                if (listId.contains(currentId)) {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }
            }
            sendJsonResponse(response, ICartService);
            return ;
        }



        response.sendRedirect(request.getContextPath() + "/personal/cart");
    }
}
