package vn.edu.nlu.fit.elearning.feature.permission.service;

import vn.edu.nlu.fit.elearning.feature.permission.dao.PermissionDao;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;

import java.util.List;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public int createPermission(Permission permission) {
        // TODO: Implement creation logic
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
        // TODO: Implement getById logic
        return null;
    }

    public void updatePermission(Permission permission) {
        // TODO: Implement update logic
    }

    public void deletePermission(int id) {
        // TODO: Implement delete logic
    }
}