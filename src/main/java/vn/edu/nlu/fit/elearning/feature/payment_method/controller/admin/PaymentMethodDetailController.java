package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;

import java.io.IOException;

@WebServlet(name = "PaymentMethodDetailController", value = "/admin/payment-method/detail")
public class PaymentMethodDetailController extends BaseController {
    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = RequestUtils.getParameterAsInt(request, "id", -1);

            if (id > 0) {
                PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(id);
                if (paymentMethod != null) {
                    request.setAttribute("paymentMethod", paymentMethod);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy phương thức thanh toán");
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID phương thức thanh toán không hợp lệ");
                return;
            }

            // Forward tới giao diện chi tiết vừa dựng
            this.forward(request, response, "/views/pages/admin/payment/payment-method-create.jsp");
        } catch (Exception e) {
            log("Unexpected error in PaymentMethodDetailController", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = RequestUtils.getParameterAsInt(request, "id", -1);
        String status = request.getParameter("status");

        try {
            // Lấy thực thể gốc từ database lên
            PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(id);
            if (paymentMethod == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy phương thức thanh toán để cập nhật");
                return;
            }

            // ĐÚNG YÊU CẦU: Chỉ cho phép cập nhật trạng thái hoạt động (status) ngoài ra không sửa gì khác
            paymentMethod.setStatus(status);

            int result = paymentMethodService.updatePaymentMethod(paymentMethod);
            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật trạng thái phương thức thanh toán thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Lỗi hệ thống! Không thể cập nhật trạng thái.");
            }

            // Chuyển hướng quay lại chính trang chi tiết vừa sửa để reload dữ liệu mới
            this.redirect(request, response, "/admin/payment-methods/detail?id=" + id);
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Lỗi hệ thống: " + e.getMessage());
            this.redirect(request, response, "/admin/payment-methods/detail?id=" + id);
        }
    }
}