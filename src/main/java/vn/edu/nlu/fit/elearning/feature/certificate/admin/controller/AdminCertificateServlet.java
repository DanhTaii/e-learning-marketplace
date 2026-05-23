package vn.edu.nlu.fit.elearning.feature.certificate.admin.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.certificate.CertificateFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateAdminDto;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.service.AdminCertificateService;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.service.AdminCertificateServiceImp;
import vn.edu.nlu.fit.elearning.feature.course.admin.controller.CourseAdminActionController;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AdminCertificateServlet", urlPatterns = "/admin/certificates")
public class AdminCertificateServlet extends BaseController {

    private transient AdminCertificateService certificateService;
    private transient CourseAdminService courseAdminService;
    private static final int PAGE_LIMIT = 16;
    private static final Logger logger = LoggerFactory.getLogger(AdminCertificateServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.certificateService = BeanContainer.getBean(AdminCertificateService.class);
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CertificateFilter filter = new CertificateFilter();

            filter.setSearchName(RequestUtils.getParameterAsString(request, "searchName", ""));
            filter.setStatus(RequestUtils.getParameterAsStatus(request, "status"));
            filter.setCourseId(RequestUtils.getParameterAsInt(request, "courseId", 0));

            filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
            filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));

            filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
            filter.setSize(RequestUtils.getParameterAsInt(request, "size", PAGE_LIMIT));

            List<CertificateAdminDto> listCertificates = certificateService.getCertificates(filter);

            int totalPages = certificateService.getTotalPages(filter);

            List<Course> listCourses = courseAdminService.getAllCourses();
            request.setAttribute("listCourse", listCourses);

            request.setAttribute("listCertificates", listCertificates);
            request.setAttribute("filter", filter);

            request.setAttribute("currentPageNumber", filter.getPage());
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalCertificates", certificateService.getTotalCertificate());
            request.setAttribute("currentPage", "certificate");

            String type = request.getParameter("renderType");
            if ("partial".equals(type)) {
                // Chỉ render phần nội dung bảng
                this.forward(request, response, "/views/pages/admin/certificate/certificate-fragment.jsp");
            } else {
                // Render toàn bộ trang như cũ
                request.getRequestDispatcher("/views/pages/admin/certificate/certificate-management.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("/views/pages/common/error-500.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");
            String action = RequestUtils.getParameterAsString(request, "action", null);

            boolean result = false;
            if (action != null) {
                switch (action) {
                    case "update_status":
                        result = certificateService.changeCertificateStatus(ids);
                        if (result) {
                            request.getSession().setAttribute("flashSuccess", "Cập nhật trạng thái chứng chỉ thành công!");
                            response.sendRedirect(request.getContextPath() + "/admin/certificates");
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
