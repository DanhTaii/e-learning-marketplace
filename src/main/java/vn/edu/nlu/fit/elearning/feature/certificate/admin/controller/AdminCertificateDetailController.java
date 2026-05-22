package vn.edu.nlu.fit.elearning.feature.certificate.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.service.AdminCertificateService;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;

@WebServlet(name = "AdminCertificateDetailController", value = "/admin/certificate/detail")
public class AdminCertificateDetailController extends HttpServlet {
    private transient AdminCertificateService certificateService;
    private transient CourseAdminService courseAdminService;
    private static final int PAGE_LIMIT = 16;
    private static final Logger logger = LoggerFactory.getLogger(AdminCertificateDetailController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.certificateService = BeanContainer.getBean(AdminCertificateService.class);
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code");

            if (code == null || code.trim().isEmpty()) {
                request.getSession().setAttribute("flashError", "Không tìm thấy mã chứng chỉ!");
                response.sendRedirect(request.getContextPath() + "/admin/certificates");
                return;
            }

            var certOpt = certificateService.getCertificateDetail(code);

            if (certOpt.isPresent()) {
                request.setAttribute("currentPage", "certificate");
                request.setAttribute("certificateDetail", certOpt.get());
                request.getRequestDispatcher("/views/pages/admin/certificate/certificate-detail-admin.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("flashError", "Chứng chỉ không tồn tại hoặc đã bị xóa!");
                response.sendRedirect(request.getContextPath() + "/admin/certificates");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String redirectUrl = request.getParameter("redirectUrl");
        if (redirectUrl == null || redirectUrl.isEmpty()) {
            redirectUrl = request.getContextPath() + "/admin/certificate/detail";
        }

        try {
            if ("changeStatus".equalsIgnoreCase(action)) {
                String code = request.getParameter("code");
                String newStatus = request.getParameter("newStatus");

                boolean success = certificateService.changeCertificateStatus(code, newStatus);

                if (success) {
                    session.setAttribute("flashSuccess", "Cập nhật trạng thái chứng chỉ thành công!");
                } else {
                    throw new Exception("Không thể cập nhật trạng thái cho mã chứng chỉ: " + code);
                }
            } else {
                session.setAttribute("flashSuccess", "Hành động không được hỗ trợ.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            session.setAttribute("flashError", "Có lỗi xảy ra: " + e.getMessage());
        }

        response.sendRedirect(redirectUrl);
    }
}