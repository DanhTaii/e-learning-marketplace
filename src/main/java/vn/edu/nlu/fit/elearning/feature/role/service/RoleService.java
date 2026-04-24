package vn.edu.nlu.fit.elearning.feature.role.service;

import vn.edu.nlu.fit.elearning.feature.role.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    int createRole(Role role);

    Role getRoleById(int id);

    List<Role> getAllRoles();

    void updateRole(Role role);

    void deleteRole(int id);

    boolean existsByName(String name);

    boolean existsByNameExcludeId(String name, int excludeId);

    Set<Integer> getPermissionIdsByRoleId(int roleId);

    void updateRolePermissions(int roleId, Set<Integer> permissionIds);
}
