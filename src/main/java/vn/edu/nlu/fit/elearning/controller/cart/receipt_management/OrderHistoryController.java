package vn.edu.nlu.fit.elearning.controller.cart.receipt_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderHistoryController", value = "/personal/order-history")

public class OrderHistoryController extends HttpServlet {

OrderService orderService;
PaymentMethodService paymentMethodService;
    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = new OrderService();
        this.paymentMethodService = new PaymentMethodService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        List<OrderDTO> order = orderService.getOrderHistoryByUserId(userId);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);


        request.setAttribute("orderList",order);

        request.getRequestDispatcher("/html-personal-cart/order-history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
