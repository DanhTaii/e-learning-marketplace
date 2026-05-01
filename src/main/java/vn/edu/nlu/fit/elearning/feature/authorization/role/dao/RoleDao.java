package vn.edu.nlu.fit.elearning.feature.authorization.role.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.role.RoleFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.role.model.Role;

import java.util.List;

public interface RoleDao {

    int create(Role role);

    Role findById(int id);

    List<Role> findAll();

    int update(Role role);

    int delete(int id);

    boolean existsByName(String name);

    boolean existsByNameExcludeId(String name, int id);

    List<Integer> getPermissionIdsByRoleId(int roleId);

    void deletePermissionsByRoleId(int roleId);

    void insertRolePermissions(int roleId, List<Integer> permissionIds);

    List<Role> findByFilter(RoleFilter filter);

    int countByFilter(RoleFilter filter);
}
