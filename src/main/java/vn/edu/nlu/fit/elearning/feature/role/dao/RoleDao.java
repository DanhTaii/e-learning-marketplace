package vn.edu.nlu.fit.elearning.feature.role.dao;

import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();
}
