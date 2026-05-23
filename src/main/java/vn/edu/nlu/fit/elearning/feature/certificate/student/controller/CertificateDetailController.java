package vn.edu.nlu.fit.elearning.feature.certificate.student.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.common.utils.servlet.SessionUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.student.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.student.service.CertificateService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "CertificateDetailController", value = "/personal/my-course/certificate")
public class CertificateDetailController extends BaseController {
    private transient CertificateService certificateService;
    private static final Logger logger = LoggerFactory.getLogger(CertificateDetailController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.certificateService = BeanContainer.getBean(CertificateService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = SessionUtils.getCurrentUserId(request);
            int courseId = RequestUtils.getParameterAsInt(request, "courseId", 0);
            CertificateDetailDto certificateDetailDto = certificateService.getCertificateByUserIdAndCourseId(userId, courseId);

            LocalDateTime issueDate = certificateDetailDto.getIssueDate().toLocalDateTime();

            request.setAttribute("issueYear", issueDate.getYear());
            request.setAttribute("issueMonth", issueDate.getMonthValue());
            request.setAttribute("certificateDetail", certificateDetailDto);
            this.forward(request, response, "/views/pages/personal/course/enrollment/certificate.jsp");
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "PHƯƠNG THỨC POST KHÔNG ĐƯỢC HỖ TRỢ !");
    }

    public static void main(String[] args) {
//        CertificateService service = new CertificateServiceImpl(new CertificateDaoImp());
//        CertificateDetailDto certificateDetailDto = service.getCertificateByUserIdAndCourseId(73, 7);
//        LocalDateTime issueDate = certificateDetailDto.getIssueDate().toLocalDateTime();
//        System.out.println(issueDate.getYear());
//        System.out.println(issueDate.getMonthValue());
//        System.out.println(certificateDetailDto.toString());
    }
}