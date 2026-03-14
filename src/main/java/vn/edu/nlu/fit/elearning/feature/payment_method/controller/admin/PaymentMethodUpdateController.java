package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;

import java.io.IOException;

@WebServlet(name = "PaymentMethodUpdateController", value = "/admin/payment-methods/update")
public class PaymentMethodUpdateController extends HttpServlet {

    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String iconUrl = request.getParameter("iconUrl");
        String status = request.getParameter("status");

        if (idStr == null || idStr.isEmpty() || name == null || name.trim().isEmpty() ||
                code == null || code.trim().isEmpty()) {
            request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin bắt buộc (ID, Tên, Code)!");
            response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setId(id);
            paymentMethod.setName(name.trim());
            paymentMethod.setCode(code.trim());
            paymentMethod.setIconUrl(iconUrl != null ? iconUrl.trim() : "");

            if ("ACTIVE".equals(status)) {
                paymentMethod.setStatus("ACTIVE");
            } else {
                paymentMethod.setStatus("INACTIVE");
            }

            int result = paymentMethodService.updatePaymentMethod(paymentMethod);

            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật phương thức thanh toán thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Cập nhật thất bại. Vui lòng thử lại!");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "ID không hợp lệ!");
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Cập nhật thất bại: Có thể Code hoặc Tên đã tồn tại trong hệ thống!");
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin/payment-methods");
    }
}