package vn.edu.nlu.fit.elearning.feature.role.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.role.model.Role;

import java.util.List;

public class RoleDaoImpl extends BaseDao implements BaseCrudDao<Role, Integer>, RoleDao {


    @Override
    public int create(Role entity) {
        String sql = "INSERT INTO roles (name, description) VALUES (:name, :description)";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("name", entity.getName())
                        .bind("description", entity.getDescription())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public Role findById(Integer id) {
        String sql = "SELECT id, name, description, created_at FROM roles WHERE id = :id";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(Role.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT id, name, description, created_at FROM roles";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Role.class)
                        .list()
        );
    }

    @Override
    public int update(Role entity) {
        String sql = "UPDATE roles SET name = :name, description = :description WHERE id = :id";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("id", entity.getId())
                        .bind("name", entity.getName())
                        .bind("description", entity.getDescription())
                        .execute()
        );
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM roles WHERE id = :id";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("id", id)
                        .execute()
        );
    }

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM roles WHERE name = :name";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("name", name)
                        .mapTo(Integer.class)
                        .findOne()
                        .isPresent()
        );
    }

    @Override
    public boolean existsByNameExcludeId(String name, int id) {
        String sql = "SELECT 1 FROM roles WHERE name = :name AND id != :id";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("name", name)
                        .bind("id", id)
                        .mapTo(Integer.class)
                        .findOne()
                        .isPresent()
        );
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(int roleId) {
        String sql = "SELECT permission_id FROM role_permissions WHERE role_id = :roleId";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("roleId", roleId)
                        .mapTo(Integer.class)
                        .list()
        );
    }

    @Override
    public void deletePermissionsByRoleId(int roleId) {
        String sql = "DELETE FROM role_permissions WHERE role_id = :roleId";

        getJdbi().useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("roleId", roleId)
                        .execute()
        );
    }

    @Override
    public void insertRolePermissions(int roleId, List<Integer> permissionIds) {

        String sql = "INSERT INTO role_permissions (role_id, permission_id) VALUES (:roleId, :permissionId)";

        getJdbi().useHandle(handle -> {
            for (Integer pid : permissionIds) {
                handle.createUpdate(sql)
                        .bind("roleId", roleId)
                        .bind("permissionId", pid)
                        .execute();
            }
        });
    }
}