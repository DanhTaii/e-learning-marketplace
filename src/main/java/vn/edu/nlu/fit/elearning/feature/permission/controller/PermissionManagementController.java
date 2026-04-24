package vn.edu.nlu.fit.elearning.feature.permission.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            List<Permission> permissions = permissionService.getAllPermissions();

            request.setAttribute("listPermissions", permissions);
            request.setAttribute("currentPage", "permissions");
            System.out.println("======================== START");
            this.forward(request, response, "/views/pages/admin/authorization/permission/permission-management.jsp");
            System.out.println("======================== END");

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}