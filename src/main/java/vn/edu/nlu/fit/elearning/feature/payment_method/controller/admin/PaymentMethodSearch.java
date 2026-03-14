package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentMethodSearch", value = "/admin/payment-methods/search")
public class PaymentMethodSearch extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = new PaymentMethodServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("searchName");

        List<PaymentMethod> listPaymentMethods;

        if (nameSearch == null || nameSearch.trim().isEmpty()) {
            // Không có từ khóa → lấy toàn bộ
            listPaymentMethods = paymentMethodService.getAllPaymentMethods();
        } else {
            // Có từ khóa → tìm theo tên
            listPaymentMethods = paymentMethodService.getAllPaymentMethodsByName(nameSearch.trim());
        }

        request.setAttribute("listPaymentMethods", listPaymentMethods);
        // Thay đường dẫn này bằng đúng file JSP của bạn
        request.getRequestDispatcher("views/pages/admin/payment/payment-method-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}