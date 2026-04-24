package vn.edu.nlu.fit.elearning.feature.role.dao;

import vn.edu.nlu.fit.elearning.feature.role.model.Role;

import java.util.List;

public interface RoleDao {

    int create(Role role);

    Role findById(Integer id);

    List<Role> findAll();

    int update(Role role);

    int delete(Integer id);

    boolean existsByName(String name);

    boolean existsByNameExcludeId(String name, int id);

    List<Integer> getPermissionIdsByRoleId(int roleId);

    void deletePermissionsByRoleId(int roleId);

    void insertRolePermissions(int roleId, List<Integer> permissionIds);
}
