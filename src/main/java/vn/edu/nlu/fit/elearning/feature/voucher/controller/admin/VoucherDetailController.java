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
import vn.edu.nlu.fit.elearning.common.helper.validator.lesson.LessonValidator;
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
    }
}