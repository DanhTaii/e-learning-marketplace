package vn.edu.nlu.fit.elearning.feature.authorization.permission.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission.PermissionFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.dao.PermissionDao;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.model.Permission;

import java.util.List;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public int createPermission(Permission permission) {
        return 0;
    }

    @Override
    public List<Permission> getAllPermissions() {
        try {
            return permissionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Permission getPermissionById(int id) {
        return null;
    }

    public void updatePermission(Permission permission) {
    }

    public void deletePermission(int id) {
    }

    @Override
    public List<Permission> getPermissionsByFilter(PermissionFilter filter) {
        return permissionDao.findPermissionsByFilter(filter);
    }

    @Override
    public int countPermissionsByFilter(PermissionFilter filter) {
        return permissionDao.countPermissionsByFilter(filter);
    }

    @Override
    public List<String> getAllGroupNames() {
        return permissionDao.findAllGroupNames();
    }
}