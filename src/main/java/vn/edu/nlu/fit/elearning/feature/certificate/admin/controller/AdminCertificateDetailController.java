package vn.edu.nlu.fit.elearning.feature.certificate.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.service.AdminCertificateService;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;
import java.util.List;

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
            int id = RequestUtils.getParameterAsInt(request, "id", 0);

            var certOpt = certificateService.getCertificateDetail(id);

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
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức POST không được hỗ trợ cho endpoint này");
    }
}