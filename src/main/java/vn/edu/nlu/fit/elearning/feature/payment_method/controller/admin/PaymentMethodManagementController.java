package vn.edu.nlu.fit.elearning.feature.payment_method.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.payment.PaymentMethodFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaymentMethodManagementController", value = "/admin/payment-methods")
public class PaymentMethodManagementController extends BaseController {

    private PaymentMethodService paymentMethodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PaymentMethodFilter filter = new PaymentMethodFilter();

        filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<PaymentMethod> listPaymentMethods = paymentMethodService.getPaymentMethodsByFilter(filter);

        int totalRecords = paymentMethodService.getCountPaymentMethodsByFilter(filter);
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("listPaymentMethods", listPaymentMethods);
        request.setAttribute("totalPaymentMethods", paymentMethodService.getTotalPaymentMethods());
        request.setAttribute("filter", filter);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "payment-methods");
        request.setAttribute("totalPages", totalPages);
        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            // Chỉ render phần nội dung bảng (Cần tách riêng bảng html ra fragment giống lesson)
            this.forward(request, response, "/views/pages/admin/payment/payment-method-fragment.jsp");
        } else {
            // Render toàn bộ trang
            this.forward(request, response, "/views/pages/admin/payment/payment-method-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = RequestUtils.getParameterAsString(request, "action", null);
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");

        String query = request.getParameter("currentQuery");
        String newPath = "/admin/payment-methods" + (query != null && !query.isEmpty() ? "?" + query : "");

        this.redirect(request, response, newPath);
    }

}
