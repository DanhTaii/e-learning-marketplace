package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.service.ICart;


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
        ICart ICart = (ICart) session.getAttribute("cart");

        if (ICart != null) {
            String[] selectedIds = request.getParameterValues("itemSelected");
            List<String> listId = (selectedIds != null) ? Arrays.asList(selectedIds) : new ArrayList<>();
            for (CartItem item : ICart.getList()) {
                String currentId = String.valueOf(item.getCourse().getId());

                if (listId.contains(currentId)) {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }
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
}
