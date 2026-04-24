package vn.edu.nlu.fit.elearning.feature.role.service;

import vn.edu.nlu.fit.elearning.feature.role.dao.RoleDao;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public int createRole(Role role) {
        return 0;
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

    public Role getRoleById(int id) {
        return null;
    }

    public void updateRole(Role role) {
    }

    public void deleteRole(int id) {
    }
}