package vn.edu.nlu.fit.elearning.feature.certificate.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.service.AdminCertificateService;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCertificateActionController", value = "/admin/certificate/action")
public class AdminCertificateActionController extends BaseController {

    private transient AdminCertificateService certificateService;
    private transient CourseAdminService courseAdminService;
    private static final int PAGE_LIMIT = 16;
    private static final Logger logger = LoggerFactory.getLogger(AdminCertificateActionController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.certificateService = BeanContainer.getBean(AdminCertificateService.class);
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức GET không được hỗ trợ cho endpoint này");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int certId = RequestUtils.getParameterAsInt(request, "id", 0);
            String deleteType = RequestUtils.getParameterAsString(request, "actionType", null);
            List<Integer> ids = List.of(certId);

            boolean result = false;
            if (deleteType != null) {
                switch (deleteType) {
                    case "revoke_cert":
                        result = certificateService.changeCertificateStatus(ids);
                        if (result) {
                            request.getSession().setAttribute("flashSuccess", "Thu hồi chứng chỉ thành công!");
                            response.sendRedirect(request.getContextPath() + "/admin/certificate/detail?id=" + certId);
                            return;
                        }
                        break;

                    case "reinstate_cert":
                        result = certificateService.changeCertificateStatus(ids);
                        if (result) {
                            request.getSession().setAttribute("flashSuccess", "Cấp lại chứng chỉ thành công!");
                            response.sendRedirect(request.getContextPath() + "/admin/certificate/detail?id=" + certId);
                            return;
                        }
                        break;

                    default:
                        handleError(request, response, "Thao tác thực hiện thất bại ! ");
                        break;
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}