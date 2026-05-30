package vn.edu.nlu.fit.elearning.feature.voucher.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonArchiveFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherArchiveFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.lesson.dto.LessonArchive;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;
import vn.edu.nlu.fit.elearning.feature.voucher.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VoucherAchievementController", value = "/admin/vouchers/archive")
public class VoucherArchiveController extends BaseController {

    private transient VoucherService voucherService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Inject VoucherService từ BeanContainer y hệt như Lesson
        this.voucherService = BeanContainer.getBean(VoucherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            VoucherArchiveFilter filter = new VoucherArchiveFilter();

            // 1. Lấy các điều kiện tìm kiếm từ request (Khớp với form tìm kiếm trên UI)
            filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));
            filter.setDeletedFromDate(RequestUtils.getParameterAsFromDate(request, "deletedFromDate", null));
            filter.setDeletedToDate(RequestUtils.getParameterAsToDate(request, "deletedToDate", null));

            // 2. Lấy thông tin phân trang (Mặc định trang 1, mỗi trang 16 bản ghi giống Lesson)
            filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
            filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

            // 3. Gọi Service lấy danh sách voucher trong thùng rác theo bộ lọc
            List<Voucher> listVouchers = voucherService.getArchivedVouchersByFilter(filter);

            // 4. Tính toán tổng số trang để hiển thị thanh phân trang (Pagination)
            int totalRecords = voucherService.getCountVouchersArchiveByFilter(filter);
            int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

            // 5. Đẩy toàn bộ dữ liệu ra Request Attribute để hiển thị lên file JSP
            request.setAttribute("archivedVouchers", listVouchers);
            request.setAttribute("totalArchived", voucherService.getTotalVouchersArchive()); // Số lượng hiển thị ở Badge tab
            request.setAttribute("filter", filter);
            request.setAttribute("currentPageNumber", filter.getPage());
            request.setAttribute("totalPages", totalPages);

            // Giữ trạng thái Active cho Menu Sidebar của Voucher
            request.setAttribute("currentPage", "vouchers");
            request.setAttribute("currentPageArchive", "vouchers");

            // 6. Kiểm tra render kiểu partial (AJAX chuyển trang) hoặc render toàn bộ trang
            String type = request.getParameter("renderType");
            if ("partial".equals(type)) {
                // Chỉ render phần nội dung bảng dữ liệu
                this.forward(request, response, "/views/pages/admin/voucher/archive/voucher-archive-fragment.jsp");
                return;
            } else {
                // Render toàn bộ cấu trúc trang như cũ
                this.forward(request, response, "/views/pages/admin/voucher/archive/voucher-archive.jsp");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy danh sách ID từ checkbox (Bulk action) hoặc id lẻ từ nút icon action (Single action)
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");
        String action = RequestUtils.getParameterAsString(request, "action", null);
        int singleId = RequestUtils.getParameterAsInt(request, "id", 0);

        if (singleId > 0) {
            ids = List.of(singleId);
        }

        String query = request.getParameter("currentQuery");
        String queryString = (query != null && !query.isEmpty()) ? "?" + query : "";
        String newPath = "/admin/vouchers/archive" + queryString;

        int result = 0;

        if (action == null || ids == null || ids.isEmpty()) {
            this.redirect(request, response, newPath);
            return;
        }

        switch (action) {
            case "delete":
                result = voucherService.deleteVouchersByIds(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Xóa vĩnh viễn " + result + " voucher thành công", newPath);
                    return;
                }
                break;

            case "restore":
                result = voucherService.restoreVouchersByIds(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Khôi phục " + result + " voucher thành công", newPath);
                    return;
                }
                break;

            default:
                handleError(request, response, "Thao tác thực hiện thất bại!");
                break;
        }

        // Nếu result = 0 (Thất bại) thì load lại trang
        this.redirect(request, response, newPath);
    }
}