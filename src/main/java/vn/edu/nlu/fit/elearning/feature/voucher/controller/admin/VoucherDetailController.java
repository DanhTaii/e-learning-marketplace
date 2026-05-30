package vn.edu.nlu.fit.elearning.feature.voucher.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.external.cloudinary.CloudinaryService;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.VoucherStatus;
import vn.edu.nlu.fit.elearning.common.helper.validator.lesson.LessonValidator;
import vn.edu.nlu.fit.elearning.common.helper.validator.voucher.VoucherValidator;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;
import vn.edu.nlu.fit.elearning.feature.voucher.service.VoucherService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoucherDetailController", value = "/admin/voucher/detail")
public class VoucherDetailController extends BaseController {
    private transient VoucherService voucherService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.voucherService = BeanContainer.getBean(VoucherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = RequestUtils.getParameterAsInt(request, "id", -1);
                Voucher voucher = voucherService.findById(id);
                if (voucher != null) {
                    request.setAttribute("voucher", voucher);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy mã giảm giá");
                    return;
                }
            }
            this.forward(request, response, "/views/pages/admin/voucher/voucher-create.jsp");
        } catch (Exception e) {
            log("Unexpected error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ bên Client truyền qua
        Voucher voucher = new Voucher();
        int id = RequestUtils.getParameterAsInt(request, "id", -1);
        voucher.setId(id);

        // Chuẩn hóa mã Code luôn viết hoa và loại bỏ khoảng trắng thừa
        String codeParam = request.getParameter("code");
        voucher.setCode(codeParam != null ? codeParam.trim().toUpperCase() : "");
        voucher.setTitle(request.getParameter("title"));
        voucher.setDescription(request.getParameter("description"));
        voucher.setDiscountType(request.getParameter("discountType"));

        try {
            String discountValueStr = request.getParameter("discountValue");
            if (discountValueStr != null && !discountValueStr.isEmpty()) {
                voucher.setDiscountValue(Integer.parseInt(discountValueStr));
            }

            String minOrderValueStr = request.getParameter("minOrderValue");
            if (minOrderValueStr != null && !minOrderValueStr.isEmpty()) {
                voucher.setMinOrderValue(Integer.parseInt(minOrderValueStr));
            } else {
                voucher.setMinOrderValue(0);
            }
            String maxDiscountValueStr = request.getParameter("maxDiscountValue");
            if (maxDiscountValueStr != null && !maxDiscountValueStr.isEmpty() && "PERCENT".equals(voucher.getDiscountType())) {
                voucher.setMaxDiscountValue(Integer.parseInt(maxDiscountValueStr));
            } else {
                voucher.setMaxDiscountValue(null);
            }

            String usageLimitStr = request.getParameter("usageLimit");
            if (usageLimitStr != null && !usageLimitStr.isEmpty()) {
                voucher.setUsageLimit(Integer.parseInt(usageLimitStr));
            }
        } catch (Exception e) {
            log("Lỗi định dạng dữ liệu số của Voucher", e);
        }

        VoucherStatus status = RequestUtils.getParameterAsVoucherStatus(request, "status");
        voucher.setStatus(status);

        // Xử lý convert thời gian từ input datetime-local (yyyy-MM-dd'T'HH:mm) sang Timestamp
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                voucher.setStartDate(parseFlexibleDateTime(startDateStr));
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                voucher.setEndDate(parseFlexibleDateTime(endDateStr));
            }
        } catch (Exception e) {
            log("Lỗi định dạng ngày tháng Voucher: " + startDateStr + " | " + endDateStr, e);
        }

        try {
            // Truyền model vô validator để kiểm tra dữ liệu (Đảm bảo bạn đã tạo lớp VoucherValidator nhé)
            Map<String, String> errors = VoucherValidator.validate(voucher);

            // Nếu như có lỗi thì gửi sang bên client để client hiển thị cùng với các giá trị đã nhập của voucher
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("voucher", voucher);
                this.doGet(request, response);
                return;
            }

            // Nếu id lớn hơn 0 thì là cập nhật, ngược lại là tạo mới
            if (voucher.getId() > 0) {
                // LOGIC CẬP NHẬT
                boolean success = voucherService.updateVoucher(voucher);
                if (success) {
                    request.getSession().setAttribute("flashSuccess", "Cập nhật mã giảm giá thành công!");
                }
                this.redirect(request, response, "/admin/voucher/detail?id=" + (voucher.getId() > 0 ? voucher.getId() : ""));
            } else {
                // LOGIC TẠO MỚI
                // Kiểm tra trùng mã code Voucher trên toàn hệ thống
                if (voucherService.checkVoucherCode(voucher.getCode())) {
                    handleError(request, response, "Mã giảm giá này đã tồn tại trên hệ thống!");
                    return;
                }

                // Result này đang trả về id của Voucher vừa được tạo ra trong DB
                int result = voucherService.createVoucher(voucher);
                if (result > 0) {
                    request.getSession().setAttribute("flashSuccess", "Tạo mã giảm giá thành công!");
                    response.sendRedirect(request.getContextPath() + "/admin/voucher/detail?id=" + result);
                } else {
                    handleError(request, response, "Lỗi hệ thống khi tạo mã giảm giá");
                }
            }
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Lỗi hệ thống: " + e.getMessage());
            this.redirect(request, response, "/admin/voucher/detail" + (id > 0 ? "?id=" + id : ""));
        }
    }
    private java.sql.Timestamp parseFlexibleDateTime(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;

        // 1. Chuẩn hóa ký tự ngăn cách: Thay thế 'T' bằng khoảng trắng để đưa về chuẩn JDBC
        String cleaned = dateStr.trim().replace("T", " ");

        // 2. Nếu chuỗi bị thiếu giây (ví dụ từ input datetime-local gửi lên: yyyy-MM-dd HH:mm)
        if (cleaned.length() == 16) {
            cleaned += ":00"; // Bổ sung giây giả lập để đạt độ dài chuẩn 19 ký tự
        }

        // 3. Ép kiểu trực tiếp sang Timestamp một cách an toàn
        return java.sql.Timestamp.valueOf(cleaned);
    }
}