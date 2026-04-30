package vn.edu.nlu.fit.elearning.feature.contact.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;
import vn.edu.nlu.fit.elearning.feature.contact.service.ContactService;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;

import java.io.IOException;

@WebServlet(name = "ContactController", value = "/api/contact")
public class ContactController extends BaseController {

    private transient ContactService contactService;
    private transient Contact contact;

    @Override
    public void init() throws ServletException {
        super.init();
        this.contactService = BeanContainer.getBean(ContactService.class);
        this.contact = new Contact();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String emailInput = request.getParameter("email");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");

            Contact contact = new Contact();

            UserShortResponse user = (UserShortResponse) request.getSession().getAttribute("userSession");

            if (user != null) {
                contact.setUserId(user.getId());
                contact.setEmail(user.getEmail());
            } else {
                contact.setUserId(null);
                contact.setEmail(emailInput);
            }

            contact.setSubject(subject);
            contact.setMessage(message);

            int result = contactService.createContact(contact);

            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Gửi liên hệ thành công!");
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                handleError(request, response, "Không thể gửi liên hệ!");
            }

            System.out.println("USER SESSION: " + user);
            System.out.println("USER ID: " + (user != null ? user.getId() : "NULL"));

        } catch (Exception e) {
            log("Error when sending contact", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }

    }
}