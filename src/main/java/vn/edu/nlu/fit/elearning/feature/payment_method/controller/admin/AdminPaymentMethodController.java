package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPaymentMethodController", value = "/admin/payment-methods")
public class AdminPaymentMethodController extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = new PaymentMethodService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PaymentMethod> listPaymentMethods = paymentMethodService.getAllPaymentMethods();
        request.setAttribute("listPaymentMethods", listPaymentMethods);
        request.setAttribute("currentPage", "payment-methods");
        request.getRequestDispatcher("/html-admin/payment-method-management.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String iconUrl = request.getParameter("iconUrl");

        if (name == null || name.trim().isEmpty()) {
            request.getSession().setAttribute("flashError", "Vui lòng nhập tên phương thức!");
            response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
            return;
        }

        try {
            String code = name.trim()
                    .toUpperCase()
                    .replaceAll("\\s+", "_")
                    .replaceAll("[^A-Z0-9_]", "");

            if (code.isEmpty()) {
                code = "METHOD_" + System.currentTimeMillis();
            }

            PaymentMethod pm = new PaymentMethod();
            pm.setName(name.trim());
            pm.setCode(code);
            pm.setIconUrl(iconUrl != null ? iconUrl.trim() : "");
            pm.setStatus("ACTIVE");

            paymentMethodService.createPaymentMethod(pm);

            request.getSession().setAttribute("flashSuccess", "Tạo phương thức thanh toán thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Lỗi: Có thể tên hoặc mã đã tồn tại!");
        }

        response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
    }
}
