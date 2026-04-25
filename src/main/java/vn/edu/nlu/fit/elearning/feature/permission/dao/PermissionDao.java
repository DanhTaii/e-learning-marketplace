package vn.edu.nlu.fit.elearning.feature.permission.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission.PermissionFilter;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PermissionDao {
    List<Permission> findAll();

    List<Permission> findPermissionsByFilter(PermissionFilter filter);

    int countPermissionsByFilter(PermissionFilter filter);

    List<String> findAllGroupNames();
}
