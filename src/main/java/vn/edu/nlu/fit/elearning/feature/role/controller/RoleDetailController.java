package vn.edu.nlu.fit.elearning.feature.role.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;
import vn.edu.nlu.fit.elearning.feature.role.service.RoleService;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.permission.service.PermissionService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "RoleDetailController", value = "/admin/super/role/detail")
public class RoleDetailController extends BaseController {

    private transient RoleService roleService;
    private transient PermissionService permissionService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.roleService = BeanContainer.getBean(RoleService.class);
        this.permissionService = BeanContainer.getBean(PermissionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Permission> permissions = permissionService.getAllPermissions();

            Map<String, List<Permission>> permissionGroups =
                    permissions.stream().collect(Collectors.groupingBy(Permission::getGroupName));

            request.setAttribute("permissionGroups", permissionGroups);

            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {

                int id = RequestUtils.getParameterAsInt(request, "id", -1);
                Role role = roleService.getRoleById(id);

                if (role == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy role!");
                    return;
                }

                request.setAttribute("role", role);

                Set<Integer> selectedPermissions =
                        roleService.getPermissionIdsByRoleId(id);

                request.setAttribute("selectedPermissions", selectedPermissions);
            }

            this.forward(request, response, "/views/pages/admin/authorization/role/role-create.jsp");

        } catch (Exception e) {
            log("Error RoleDetail GET", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Role role = new Role();

            int id = RequestUtils.getParameterAsInt(request, "id", -1);
            role.setId(id);

            role.setName(request.getParameter("name"));
            role.setDescription(request.getParameter("description"));

            String[] permissionIdsRaw = request.getParameterValues("permissionIds");

            Set<Integer> permissionIds = new HashSet<>();

            if (permissionIdsRaw != null) {
                for (String pid : permissionIdsRaw) {
                    permissionIds.add(Integer.parseInt(pid));
                }
            }

            Map<String, String> errors = new HashMap<>();

            if (role.getName() == null || role.getName().trim().isEmpty()) {
                errors.put("name", "Tên role không được để trống!");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("role", role);

                List<Permission> permissions = permissionService.getAllPermissions();
                Map<String, List<Permission>> permissionGroups =
                        permissions.stream().collect(Collectors.groupingBy(Permission::getGroupName));

                request.setAttribute("permissionGroups", permissionGroups);
                request.setAttribute("selectedPermissions", permissionIds);

                this.forward(request, response, "/views/pages/admin/authorization/role/role-create.jsp");
                return;
            }

            if (role.getId() > 0) {

                if (roleService.existsByNameExcludeId(role.getName(), role.getId())) {
                    handleError(request, response, "Tên role đã tồn tại!");
                    return;
                }

                roleService.updateRole(role);
                roleService.updateRolePermissions(role.getId(), permissionIds);

                request.getSession().setAttribute("flashSuccess", "Cập nhật role thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/roles");
                return;
            }

            if (roleService.existsByName(role.getName())) {
                handleError(request, response, "Tên role đã tồn tại!");
                return;
            }

            int newRoleId = roleService.createRole(role);

            if (newRoleId > 0) {
                roleService.updateRolePermissions(newRoleId, permissionIds);

                request.getSession().setAttribute("flashSuccess", "Tạo role thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/roles");
            } else {
                handleError(request, response, "Không thể tạo role!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống!");
            this.forward(request, response, "/views/pages/admin/authorization/role/role-create.jsp");
        }
    }
}