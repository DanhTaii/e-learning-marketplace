package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user.UserFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManagementController", value = "/admin/users")
public class UserManagementController extends BaseController {

    private transient UserAdminService userAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userAdminService = BeanContainer.getBean(UserAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserFilter filter = new UserFilter();

        filter.setUsername(RequestUtils.getParameterAsString(request, "username", ""));
        filter.setEmail(RequestUtils.getParameterAsString(request, "email", ""));
        filter.setRoleName(RequestUtils.getParameterAsString(request, "roleName", ""));
        filter.setStatus(RequestUtils.getParameterAsStatus(request, "status"));
        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));

        String hasCourse = RequestUtils.getParameterAsString(request, "hasCourse", "");

        if (!hasCourse.isBlank()) {
            filter.setHasCourse(Boolean.parseBoolean(hasCourse));
        }

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<UserAdminDto> listUsers = userAdminService.getUsersByFilter(filter);

        int totalRecords = userAdminService.countUsersByFilter(filter);
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("listUsers", listUsers);
        request.setAttribute("totalUsers", totalRecords);
        request.setAttribute("filter", filter);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "users");
        request.setAttribute("totalPages", totalPages);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            this.forward(request, response, "/views/pages/admin/user/user-fragment.jsp");
        } else {
            this.forward(request, response, "/views/pages/admin/user/user-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức POST không được hỗ trợ cho endpoint này");
    }
}
