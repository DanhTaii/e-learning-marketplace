package vn.edu.nlu.fit.elearning.feature.permission.dao;

import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> findAll();
}
