package vn.edu.nlu.fit.elearning.feature.certificate.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.dao.CertificateDaoImp;
import vn.edu.nlu.fit.elearning.feature.certificate.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.service.CertificateService;
import vn.edu.nlu.fit.elearning.feature.certificate.service.CertificateServiceImpl;

import java.io.IOException;

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
            HttpSession session = request.getSession();
            int userId = 0;
            if (session.getAttribute("userId") != null) {
                userId = (Integer) session.getAttribute("userId");
            }
            int courseId = RequestUtils.getParameterAsInt(request, "courseId", 0);
            CertificateDetailDto certificateDetailDto = certificateService.getCertificateByUserIdAndCourseId(userId, courseId);

            request.setAttribute("certificateDetail", certificateDetailDto);
            this.forward(request, response, "/views/pages/personal/course/enrollment/certificate.jsp");
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static void main(String[] args) {
//        CertificateService service = new CertificateServiceImpl(new CertificateDaoImp());
//        CertificateDetailDto certificateDetailDto = service.getCertificateByUserIdAndCourseId(73, 7);
//        System.out.println(certificateDetailDto.toString());
    }
}