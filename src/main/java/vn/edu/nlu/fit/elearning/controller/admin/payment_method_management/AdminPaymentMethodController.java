package vn.edu.nlu.fit.elearning.controller.admin.payment_method_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.services.PaymentMethodService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPaymentMethodController", value = "/admin/payment-methods")
public class AdminPaymentMethodController extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    public AdminPaymentMethodController() {
        this.paymentMethodService = new PaymentMethodService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PaymentMethod> listPaymentMethods = paymentMethodService.getAllPaymentMethods();
        request.setAttribute("listPaymentMethods", listPaymentMethods);
        request.getRequestDispatcher("/html-admin/payment-method-management.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String iconUrl = request.getParameter("iconUrl");
        String activeParam = request.getParameter("active");

        if (name == null || name.isEmpty() || code == null || code.isEmpty()) {
            request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin!");
            request.setAttribute("listPaymentMethods",
                    paymentMethodService.getAllPaymentMethods());
            request.getRequestDispatcher("/html-admin/payment-method-management.jsp")
                    .forward(request, response);
            return;
        }

        try {
            PaymentMethod pm = new PaymentMethod();
            pm.setName(name);
            pm.setCode(code);
            pm.setIconUrl(iconUrl);
            pm.setActive(activeParam != null); // checkbox

            int checkCreate = paymentMethodService.createPaymentMethod(pm);

            if (checkCreate == 1) {
                request.getSession().setAttribute("flashSuccess",
                        "Tạo phương thức thanh toán thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
            }

        } catch (Exception e) {
            request.getSession().setAttribute("flashError",
                    "Tên hoặc mã phương thức đã tồn tại!");
            request.setAttribute("oldName", name);
            request.setAttribute("oldCode", code);
            request.setAttribute("oldIconUrl", iconUrl);
            request.setAttribute("listPaymentMethods",
                    paymentMethodService.getAllPaymentMethods());
            request.getRequestDispatcher("/html-admin/payment-method-management.jsp")
                    .forward(request, response);
        }
    }
}
