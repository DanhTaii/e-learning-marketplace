package vn.edu.nlu.fit.elearning.feature.authorization.role.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.role.RoleFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.role.model.Role;

import java.util.List;

public class RoleDaoImpl extends BaseDao implements RoleDao {

    @Override
    public int create(Role role) {
        String sql = "INSERT INTO roles(name, description, status) VALUES (:name, :description, :status)";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("name", role.getName())
                        .bind("description", role.getDescription())
                        .bind("status", role.getStatus().name())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    @Override
    public Role findById(int id) {
        String sql = "SELECT id, name, description, created_at, updated_at, status FROM roles WHERE id = :id";

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
        String sql = "SELECT id, name, description, created_at, updated_at, status FROM roles ORDER BY created_at DESC";

        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Role.class)
                        .list()
        );
    }

    @Override
    public int update(Role role) {
        String sql = "UPDATE roles SET name = :name, description = :description, status = :status WHERE id = :id";

        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("id", role.getId())
                        .bind("name", role.getName())
                        .bind("description", role.getDescription())
                        .bind("status", role.getStatus().name())
                        .execute()
        );
    }

    @Override
    public int delete(int id) {
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
        String sql = "INSERT INTO role_permissions(role_id, permission_id) VALUES (:roleId, :permissionId)";

        getJdbi().useHandle(handle -> {
            for (Integer pid : permissionIds) {
                handle.createUpdate(sql)
                        .bind("roleId", roleId)
                        .bind("permissionId", pid)
                        .execute();
            }
        });
    }

    @Override
    public List<Role> findByFilter(RoleFilter filter) {

        StringBuilder sql = new StringBuilder("""
        SELECT DISTINCT r.id, r.name, r.description, r.created_at, r.updated_at, status
        FROM roles r
        LEFT JOIN role_permissions rp ON r.id = rp.role_id
        WHERE 1=1
    """);

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            sql.append(" AND r.name LIKE :name ");
        }

        if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
            sql.append(" AND r.description LIKE :description ");
        }

        if (filter.getPermissionId() != null) {
            sql.append(" AND rp.permission_id = :permissionId ");
        }

        if (filter.getFromDate() != null) {
            sql.append(" AND r.created_at >= :fromDate ");
        }

        if (filter.getToDate() != null) {
            sql.append(" AND r.created_at <= :toDate ");
        }

        sql.append(" ORDER BY r.created_at DESC ");
        sql.append(" LIMIT :limit OFFSET :offset ");

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(sql.toString());

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                query.bind("name", "%" + filter.getName() + "%");
            }

            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                query.bind("description", "%" + filter.getDescription() + "%");
            }

            if (filter.getPermissionId() != null) {
                query.bind("permissionId", filter.getPermissionId());
            }

            if (filter.getFromDate() != null) {
                query.bind("fromDate", filter.getFromDate());
            }

            if (filter.getToDate() != null) {
                query.bind("toDate", filter.getToDate());
            }

            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());

            return query.mapToBean(Role.class).list();
        });
    }

    @Override
    public int countByFilter(RoleFilter filter) {

        StringBuilder sql = new StringBuilder("""
        SELECT COUNT(DISTINCT r.id)
        FROM roles r
        LEFT JOIN role_permissions rp ON r.id = rp.role_id
        WHERE 1=1
    """);

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            sql.append(" AND r.name LIKE :name ");
        }

        if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
            sql.append(" AND r.description LIKE :description ");
        }

        if (filter.getPermissionId() != null) {
            sql.append(" AND rp.permission_id = :permissionId ");
        }

        if (filter.getFromDate() != null) {
            sql.append(" AND r.created_at >= :fromDate ");
        }

        if (filter.getToDate() != null) {
            sql.append(" AND r.created_at <= :toDate ");
        }

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(sql.toString());

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                query.bind("name", "%" + filter.getName() + "%");
            }

            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                query.bind("description", "%" + filter.getDescription() + "%");
            }

            if (filter.getPermissionId() != null) {
                query.bind("permissionId", filter.getPermissionId());
            }

            if (filter.getFromDate() != null) {
                query.bind("fromDate", filter.getFromDate());
            }

            if (filter.getToDate() != null) {
                query.bind("toDate", filter.getToDate());
            }

            return query.mapTo(Integer.class).one();
        });
    }
}