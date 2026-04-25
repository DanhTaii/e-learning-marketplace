package vn.edu.nlu.fit.elearning.feature.permission.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission.PermissionFilter;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getAllPermissions();

    List<Permission> getPermissionsByFilter(PermissionFilter filter);

    int countPermissionsByFilter(PermissionFilter filter);

    List<String> getAllGroupNames();
}
