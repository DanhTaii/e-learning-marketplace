package vn.edu.nlu.fit.elearning.feature.payment.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.cart.service.ICart;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.ICategoryService;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {
    PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = new PaymentMethodServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ICart ICart = (ICart) session.getAttribute("cart");
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        UserService userService = new UserServiceImpl();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        if (ICart == null || ICart.getSelectedQuantity() == 0) {
            response.sendRedirect(request.getContextPath() + "/personal/cart");
            return;
        }

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        ICategoryService ICategoryService = new CategoryService();
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());

        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
        request.setAttribute("paymentMethod", paymentMethods);
        request.getRequestDispatcher("views/pages/cart/payment.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}