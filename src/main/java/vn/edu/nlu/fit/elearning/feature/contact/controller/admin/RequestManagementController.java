package vn.edu.nlu.fit.elearning.feature.contact.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.ContactStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user_request.RequestFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;
import vn.edu.nlu.fit.elearning.feature.contact.service.ContactService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RequestManagementController", value = "/admin/requests")
public class RequestManagementController extends BaseController {

    private transient ContactService contactService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.contactService = BeanContainer.getBean(ContactService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestFilter filter = new RequestFilter();

        filter.setEmail(RequestUtils.getParameterAsString(request, "email", ""));
        filter.setSubject(RequestUtils.getParameterAsString(request, "subject", ""));

        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));

        String statusParam = request.getParameter("status");

        if (statusParam != null && !statusParam.isBlank()) {
            filter.setStatus(ContactStatus.valueOf(statusParam));
        }

        List<Contact> listRequests = contactService.getContactsByFilter(filter);

        request.setAttribute("filter", filter);
        request.setAttribute("listRequests", listRequests);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            this.forward(request, response,
                    "/views/pages/admin/user-request/request-fragment.jsp");
        } else {
            this.forward(request, response,
                    "/views/pages/admin/user-request/request-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức POST không được hỗ trợ cho endpoint này");
    }
}