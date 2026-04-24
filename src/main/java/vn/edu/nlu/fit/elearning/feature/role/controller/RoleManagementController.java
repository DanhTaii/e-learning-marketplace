package vn.edu.nlu.fit.elearning.feature.role.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.role.RoleFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.permission.service.PermissionService;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;
import vn.edu.nlu.fit.elearning.feature.role.service.RoleService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleManagementController", value = "/admin/super/roles")
public class RoleManagementController extends BaseController {

    private transient RoleService roleService;
    private transient PermissionService permissionService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.roleService = BeanContainer.getBean(RoleService.class);
        this.permissionService = BeanContainer.getBean(PermissionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            RoleFilter filter = new RoleFilter();

            filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));
            filter.setDescription(RequestUtils.getParameterAsString(request, "description", ""));

            Integer permissionId = RequestUtils.getParameterAsInt(request, "permissionId", -1);
            filter.setPermissionId(permissionId == -1 ? null : permissionId);

            filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
            filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));

            filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
            filter.setSize(RequestUtils.getParameterAsInt(request, "size", 10));

            List<Role> listRoles = roleService.getRolesByFilter(filter);

            List<Permission> listPermissions = permissionService.getAllPermissions();
            request.setAttribute("listPermissions", listPermissions);

            int totalRecords = roleService.countRolesByFilter(filter);
            int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

            request.setAttribute("listRoles", listRoles);
            request.setAttribute("filter", filter);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPageNumber", filter.getPage());

            this.forward(request, response, "/views/pages/admin/authorization/role/role-management.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}