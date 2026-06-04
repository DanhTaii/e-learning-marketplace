package vn.edu.nlu.fit.elearning.feature.certificate.student.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.certificate.student.dto.CertificateInfo;
import vn.edu.nlu.fit.elearning.feature.certificate.student.service.CertificateService;
import vn.edu.nlu.fit.elearning.feature.certificate.student.service.CertificateServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "VerifyCertificateController", value = "/certificate/verify")
public class VerifyCertificateController extends HttpServlet {

    private final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    private CertificateService certificateService;

    @Override
    public void init() throws ServletException {
        this.certificateService = BeanContainer.getBean(CertificateService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/pages/partial/verify-certificate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (code == null || code.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Vui lòng nhập mã chứng chỉ\"}");
            return;
        }

        code = code.trim();

        if (code.length() > 50) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Mã chứng chỉ không hợp lệ (vượt quá 50 ký tự)\"}");
            return;
        }

        String regexPattern = "^CERT-C\\d+-U\\d+-[A-Z0-9]{5}-\\d{4}$";
        if (!code.matches(regexPattern)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Mã chứng chỉ sai định dạng hệ thống\"}");
            return;
        }

        Optional<CertificateInfo> certificateInfo = certificateService.verifyCertificate(code);
        System.out.println(certificateInfo.toString());

        if (certificateInfo.isPresent()) {
            response.getWriter().write(gson.toJson(certificateInfo.get()));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Không tìm thấy chứng chỉ\"}");
        }
    }
}
