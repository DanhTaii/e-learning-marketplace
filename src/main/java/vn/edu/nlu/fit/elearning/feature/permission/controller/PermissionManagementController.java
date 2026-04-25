package vn.edu.nlu.fit.elearning.feature.permission.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission.PermissionFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.permission.service.PermissionService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PermissionManagementController", value = "/admin/super/permissions")
public class PermissionManagementController extends BaseController {

    private transient PermissionService permissionService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.permissionService = BeanContainer.getBean(PermissionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PermissionFilter filter = new PermissionFilter();

        List<String> groups = permissionService.getAllGroupNames();

        filter.setName(request.getParameter("searchName"));
        filter.setDescription(request.getParameter("description"));
        filter.setGroupName(request.getParameter("groupName"));

        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<Permission> permissions = permissionService.getPermissionsByFilter(filter);
        int totalRecords = permissionService.countPermissionsByFilter(filter);

        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("listPermissionGroups", groups);
        request.setAttribute("listPermissions", permissions);
        request.setAttribute("filter", filter);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "permissions");

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            forward(request, response, "/views/pages/admin/authorization/permission/permission-fragment.jsp");
        } else {
            forward(request, response, "/views/pages/admin/authorization/permission/permission-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}