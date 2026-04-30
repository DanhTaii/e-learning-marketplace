package vn.edu.nlu.fit.elearning.feature.authorization.permission.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission.PermissionFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.model.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> findAll();

    List<Permission> findPermissionsByFilter(PermissionFilter filter);

    int countPermissionsByFilter(PermissionFilter filter);

    List<String> findAllGroupNames();
}
