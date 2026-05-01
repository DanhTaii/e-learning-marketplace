package vn.edu.nlu.fit.elearning.feature.authorization.role.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.role.RoleFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.role.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    int createRole(Role role);

    void updateRole(Role role);

    void deleteRole(int id);

    Role getRoleById(int id);

    List<Role> getAllRoles();

    boolean existsByName(String name);

    boolean existsByNameExcludeId(String name, int id);

    Set<Integer> getPermissionIdsByRoleId(int roleId);

    void updateRolePermissions(int roleId, Set<Integer> permissionIds);

    List<Role> getRolesByFilter(RoleFilter filter);

    int countRolesByFilter(RoleFilter filter);
}
