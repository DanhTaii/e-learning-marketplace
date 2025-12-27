package vn.edu.nlu.fit.elearning.controller.admin.payment_method_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.services.PaymentMethodService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentMethodSearch", value = "/admin/payment-methods/search")
public class PaymentMethodSearch extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    public PaymentMethodSearch() {
        this.paymentMethodService = new PaymentMethodService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("searchName");

        List<vn.edu.nlu.fit.elearning.model.PaymentMethod> listPaymentMethods;

        if (nameSearch == null || nameSearch.trim().isEmpty()) {
            // Không có từ khóa → lấy toàn bộ
            listPaymentMethods = paymentMethodService.getAllPaymentMethods();
        } else {
            // Có từ khóa → tìm theo tên
            listPaymentMethods = paymentMethodService.getAllPaymentMethodsByName(nameSearch.trim());
        }

        request.setAttribute("listPaymentMethods", listPaymentMethods);
        // Thay đường dẫn này bằng đúng file JSP của bạn
        request.getRequestDispatcher("/html-admin/payment-method-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nếu bạn muốn hỗ trợ tìm kiếm bằng POST thì để đây, hiện tại để trống theo mẫu
    }
}