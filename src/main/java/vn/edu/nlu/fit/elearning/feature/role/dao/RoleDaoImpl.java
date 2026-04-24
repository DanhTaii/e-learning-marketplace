package vn.edu.nlu.fit.elearning.feature.role.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;

import java.util.List;

public class RoleDaoImpl extends BaseDao implements BaseCrudDao<Role, Integer>, RoleDao {


    @Override
    public int create(Role entity) {
        return 0;
    }

    @Override
    public Role findById(Integer integer) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT id, name, description, created_at " +
                "FROM roles ";

        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapToBean(Role.class)
                    .list();
        });
    }

    @Override
    public int update(Role entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }
}