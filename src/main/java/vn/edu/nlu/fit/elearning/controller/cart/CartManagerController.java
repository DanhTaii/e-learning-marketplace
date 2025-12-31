package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.model.Cart;

import java.io.IOException;

@WebServlet(name = "CartManagerController", value = "/cart-manager")

public class CartManagerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null && action != null) {
            switch (action) {
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    cart.deleteCourse(id);
                    break;

                case "removeSelected":
                    cart.removeSelected();
                    break;

                case "selectAll":
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    cart.selectAll(status);
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
