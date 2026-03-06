package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.cart.model.Cart;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "UpdateSelectController", value = "/update-select")
public class UpdateSelectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            String[] selectedIds = request.getParameterValues("itemSelected");
            List<String> listId = (selectedIds != null) ? Arrays.asList(selectedIds) : new ArrayList<>();
            for (CartItem item : cart.getList()) {
                String currentId = String.valueOf(item.getCourse().getId());

                if (listId.contains(currentId)) {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }
            }
        }


        response.sendRedirect(request.getContextPath() + "/personal/cart");
    }
}
