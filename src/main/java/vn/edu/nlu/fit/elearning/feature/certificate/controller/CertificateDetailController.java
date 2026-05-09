package vn.edu.nlu.fit.elearning.feature.certificate.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;

import java.io.IOException;

@WebServlet(name = "CertificateDetailController", value = "/personal/my-course/certificate")
public class CertificateDetailController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CertificateDetailController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.forward(request, response, "/views/pages/personal/course/enrollment/certificate.jsp");
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}