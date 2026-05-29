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

        VoucherFilter filter = new VoucherFilter();
        filter.setName(RequestUtils.getParameterAsString(request, "name", ""));
        filter.setDiscountType(RequestUtils.getParameterAsString(request, "discountType", ""));
        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
        filter.setStatus(RequestUtils.getParameterAsVoucherStatus(request, "status"));

        // Nhận diện trạng thái checkbox "Sắp hết hạn" từ bộ lọc JSP
        boolean expiredSoon = request.getParameter("expiredSoon") != null;
        filter.setExpiredSoon(expiredSoon);
        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        // 2. Gọi nghiệp vụ Service lấy dữ liệu và tổng số lượng ban đầu
        List<Voucher> listVouchers = voucherService.searchVouchers(filter);
        request.setAttribute("listVoucher", listVouchers);
        request.setAttribute("totalVouchers", voucherService.findAll().size()); // Hoặc hàm đếm tổng tất cả tương đương trong hệ thống
        request.setAttribute("filter", filter);

        int totalRecords = voucherService.getCountVouchersByFilter(filter);
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "vouchers");
        request.setAttribute("totalPages", totalPages);

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