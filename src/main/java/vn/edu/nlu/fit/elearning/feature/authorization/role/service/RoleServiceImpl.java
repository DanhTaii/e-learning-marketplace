package vn.edu.nlu.fit.elearning.feature.authorization.role.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.role.RoleFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.role.dao.RoleDao;
import vn.edu.nlu.fit.elearning.feature.authorization.role.model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public int createRole(Role role) {
        return roleDao.create(role);
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return roleDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.update(role);
    }

    @Override
    public void deleteRole(int id) {
        roleDao.delete(id);
    }

    @Override
    public boolean existsByName(String name) {
        return roleDao.existsByName(name);
    }

    @Override
    public boolean existsByNameExcludeId(String name, int excludeId) {
        return roleDao.existsByNameExcludeId(name, excludeId);
    }

    @Override
    public Set<Integer> getPermissionIdsByRoleId(int roleId) {
        return new HashSet<>(roleDao.getPermissionIdsByRoleId(roleId));
    }

    @Override
    public void updateRolePermissions(int roleId, Set<Integer> permissionIds) {

        roleDao.deletePermissionsByRoleId(roleId);

        if (permissionIds != null && !permissionIds.isEmpty()) {
            roleDao.insertRolePermissions(roleId, new ArrayList<>(permissionIds));
        }
    }

    @Override
    public List<Role> getRolesByFilter(RoleFilter filter) {
        return roleDao.findByFilter(filter);
    }

    @Override
    public int countRolesByFilter(RoleFilter filter) {
        return roleDao.countByFilter(filter);
    }
}