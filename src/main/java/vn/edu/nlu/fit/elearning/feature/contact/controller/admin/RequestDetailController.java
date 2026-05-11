package vn.edu.nlu.fit.elearning.feature.contact.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;
import vn.edu.nlu.fit.elearning.feature.contact.service.ContactService;

import java.io.IOException;

@WebServlet(name = "RequestDetailController", value = "/admin/request/detail")
public class RequestDetailController extends BaseController {

    private ContactService contactService;

    @Override
    public void init() {
        this.contactService = BeanContainer.getBean(ContactService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = RequestUtils.getParameterAsInt(request, "id", -1);
                System.out.println("ID = " + id);

                Contact contact = contactService.getContactById(id);

                System.out.println(contact);
                if (contact != null) {
                    request.setAttribute("contact", contact);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy yêu cầu hỗ trợ!");
                    return;
                }
            }
            forward(request, response, "/views/pages/admin/user-request/request-detail.jsp");
        } catch (Exception e) {
            log("Unexpected error", e);
            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Lỗi hệ thống"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}