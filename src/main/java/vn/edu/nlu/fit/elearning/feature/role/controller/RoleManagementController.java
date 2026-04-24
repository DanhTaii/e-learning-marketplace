package vn.edu.nlu.fit.elearning.feature.role.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.permission.service.PermissionService;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;
import vn.edu.nlu.fit.elearning.feature.role.service.RoleService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleManagementController", value = "/admin/super/roles")
public class RoleManagementController extends BaseController {

    private transient RoleService roleService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.roleService = BeanContainer.getBean(RoleService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Role> listRoles = roleService.getAllRoles();
            request.setAttribute("listRoles", listRoles);
            request.setAttribute("currentPage", "listRoles");
            this.forward(request, response, "/views/pages/admin/authorization/role/role-management.jsp");

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}