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
        // 1. Lấy các tham số từ request
        String action = RequestUtils.getParameterAsString(request, "action", null);
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");
        String deleteReason = RequestUtils.getParameterAsString(request, "deleteReason", null);

        // 2. Xử lý đường dẫn redirect (có giữ lại các query parameter như search, filter, page...)
        String query = request.getParameter("currentQuery");
        String queryString = (query != null && !query.isEmpty()) ? "?" + query : "";
        String newPath = "/admin/vouchers" + queryString; // Đổi đường dẫn thành /admin/vouchers

        int result = 0;

        // Nếu không có action hoặc không có id nào được chọn thì quay lại trang hiện tại
        if (action == null || ids == null || ids.isEmpty()) {
            this.redirect(request, response, newPath);
            return;
        }

        // 3. Xử lý các action tương ứng
        switch (action) {
            case "archive":
                if (deleteReason == null || deleteReason.isEmpty()) {
                    deleteReason = "Không có lý do cụ thể";
                }
                // Gọi tới hàm lưu trữ của VoucherService
                result = voucherService.archiveVouchersByIds(ids, deleteReason);
                if (result > 0) {
                    handleSuccess(request, response, "Lưu trữ " + result + " voucher thành công", newPath);
                    return;
                }
                break;

            case "update_status":
                // Gọi tới hàm cập nhật trạng thái của VoucherService
                result = voucherService.changeVouchersStatusByIds(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Cập nhật trạng thái " + result + " voucher thành công", newPath);
                    return;
                }
                break;

            case "duplicate":
                result = voucherService.bulkDuplicateVouchers(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Nhân bản " + result + " voucher thành công", newPath);
                    return;
                }
                break;

            default:
                break;
        }

        // Nếu không có result > 0 (nghĩa là xử lý thất bại) thì redirect bình thường
        this.redirect(request, response, newPath);
    }

}