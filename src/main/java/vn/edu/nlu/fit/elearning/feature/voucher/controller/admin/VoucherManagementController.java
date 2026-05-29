package vn.edu.nlu.fit.elearning.feature.voucher.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;
import vn.edu.nlu.fit.elearning.feature.voucher.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminVoucherController", value = "/admin/vouchers")
public class VoucherManagementController extends BaseController {

    private transient VoucherService voucherService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.voucherService = BeanContainer.getBean(VoucherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        // Kiểm tra cơ chế render AJAX Partial hay Toàn trang (Full-page)
        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            this.forward(request, response, "/views/pages/admin/voucher/voucher-fragment.jsp");
        } else {
            this.forward(request, response, "/views/pages/admin/voucher/voucher-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức POST không được hỗ trợ cho endpoint này");
    }
}