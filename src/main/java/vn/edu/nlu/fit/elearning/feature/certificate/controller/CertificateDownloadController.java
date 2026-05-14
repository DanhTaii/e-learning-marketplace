package vn.edu.nlu.fit.elearning.feature.certificate.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.common.utils.servlet.SessionUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.service.CertificateService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "CertificateDownloadController", value = "/personal/my-course/certificate/download")
public class CertificateDownloadController extends BaseController {
    private transient CertificateService certificateService;
    private static final Logger logger = LoggerFactory.getLogger(CertificateDownloadController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.certificateService = BeanContainer.getBean(CertificateService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = RequestUtils.getParameterAsString(request, "action", null);
            int userId = SessionUtils.getCurrentUserId(request);
            int courseId = RequestUtils.getParameterAsInt(request, "courseId", 0);
            String certificatePageUrl = "/personal/my-course/certificate?courseId=" + courseId;

            if ("download".equals(action)) {
                CertificateDetailDto cert = certificateService.getCertificateByUserIdAndCourseId(userId, courseId);

                // KIỂM TRA ĐÃ CÓ RECORD CỦA CERTIFICATE CHƯA
                if (cert == null || cert.getPdfUrl() == null) {
                    SessionUtils.setFlashError(request, "Chứng chỉ không tồn tại !");
                    this.redirect(request, response, certificatePageUrl);
                    return;
                }

                // LẤY RA FILE PDF ĐÓ DỰA TRÊN ĐƯỜNG DẪN CỦA MÁY CHỦ HIỆN TẠI + ĐƯỜNG DẪN FILE
                String absolutePath = request.getServletContext().getRealPath(cert.getPdfUrl());
                File fileDownload = new File(absolutePath);

                // KIỂM TRA FILE TỒN TẠI
                if (!fileDownload.exists()) {
                    SessionUtils.setFlashError(request, "File vật lý không tồn tại !");
                    this.redirect(request, response, certificatePageUrl);
                    return;
                }

                // SETUP BÊN TRÌNH DUYỆT: KÊU TẢI FILE
                // MẸO: GIỐNG NHƯ KIỂU SETUP LÀ DẠNG JSON ĐỂ LÀM AJAX
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"Chung-Chi-" + cert.getCertificateCode() + ".pdf\"");
                response.setContentLength((int) fileDownload.length());

                //THỰC HIỆN TRUYỀN FILE BẰNG BYTE
                try (FileInputStream inputStream = new FileInputStream(fileDownload)) {
                    OutputStream outputStream = response.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int byteRead = 0;
                    while ((byteRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, byteRead);
                    }
                }
                return;
            } else {
                SessionUtils.setFlashError(request, "Chứng chỉ không tồn tại !");
                this.redirect(request, response, certificatePageUrl);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Phương thức POST không được hỗ trợ !");
    }
}