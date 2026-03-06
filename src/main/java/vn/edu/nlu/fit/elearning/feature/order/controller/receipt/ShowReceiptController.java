package vn.edu.nlu.fit.elearning.feature.order.controller.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order_item.dto.OrderItemDTO;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowReceiptController", value = "/receipt")

public class ShowReceiptController extends HttpServlet {
OrderItemService orderItemService;
OrderService orderService;
PaymentMethodService paymentMethodService;
    @Override
    public void init() throws ServletException {
        super.init();
        this.orderItemService = new OrderItemService();
        this.orderService = new OrderService();
        this.paymentMethodService = new PaymentMethodService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
         int orderId = Integer.parseInt(request.getParameter("orderId"));

        Order order = orderService.getOrderById(orderId);
        List<OrderItemDTO> orderItemList = orderItemService.getReceiptByOrderId(orderId);
        PaymentMethod pm = paymentMethodService.getPaymentMethodById(order.getPaymentMethodId());
        request.setAttribute("order",order);
        request.setAttribute("orderItemList",orderItemList);
        request.setAttribute("paymentMethod",pm);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/html-personal-cart/receipt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
