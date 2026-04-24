package vn.edu.nlu.fit.elearning.feature.permission.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;


import java.util.List;

public class PermissionDaoImpl extends BaseDao implements BaseCrudDao<Permission, Integer>, PermissionDao {

    @Override
    public int create(Permission entity) {
        int result = 0;
        return   result;
    }

    @Override
    public Permission findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    public List<Permission> findAll() {
        String sql = "SELECT id, name, description, created_at, group_name " +
                "FROM permissions ";

        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapToBean(Permission.class)
                    .list();
        });
    }

    @Override
    public int update(Permission entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }

}