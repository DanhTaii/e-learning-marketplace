package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;

import java.io.IOException;

@WebServlet(name = "PaymentMethodDeleteController", value = "/admin/payment-methods/delete")
public class PaymentMethodDeleteController extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = new PaymentMethodServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                boolean success = paymentMethodService.deletePaymentMethod(id);

                if (success) {
                    request.getSession().setAttribute("flashSuccess", "Xóa phương thức thanh toán thành công!");
                } else {
                    request.getSession().setAttribute("flashError", "Xóa phương thức thanh toán thất bại. Vui lòng thử lại!");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("flashError", "ID phương thức thanh toán không hợp lệ!");
            }
        } else {
            request.getSession().setAttribute("flashError", "Không tìm thấy ID phương thức thanh toán!");
        }

        // Redirect về trang danh sách
        response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
    }
}