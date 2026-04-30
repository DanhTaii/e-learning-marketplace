package vn.edu.nlu.fit.elearning.feature.authorization.permission.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission.PermissionFilter;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.model.Permission;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionDaoImpl extends BaseDao implements BaseCrudDao<Permission, Integer>, PermissionDao {

    @Override
    public int create(Permission entity) {
        int result = 0;
        return   result;
    }

    @Override
    public Permission findById(Integer id) {
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
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public List<String> findAllGroupNames() {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
            SELECT DISTINCT group_name
            FROM permissions
            WHERE group_name IS NOT NULL
        """)
                        .mapTo(String.class)
                        .list()
        );
    }

    public List<Permission> findPermissionsByFilter(PermissionFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildPermissionWhereClause(filter, params);

        String sql = """
        SELECT id, name, description, group_name, created_at
        FROM permissions p
    """ + where + " ORDER BY created_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());

            return query.mapToBean(Permission.class).list();
        });
    }

    public int countPermissionsByFilter(PermissionFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildPermissionWhereClause(filter, params);

        String sql = "SELECT COUNT(*) FROM permissions p " + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildPermissionWhereClause(PermissionFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            where.append(" AND p.name LIKE :name ");
            params.put("name", "%" + filter.getName().trim() + "%");
        }

        if (filter.getDescription() != null && !filter.getDescription().trim().isEmpty()) {
            where.append(" AND p.description LIKE :description ");
            params.put("description", "%" + filter.getDescription().trim() + "%");
        }

        if (filter.getGroupName() != null && !filter.getGroupName().trim().isEmpty()) {
            where.append(" AND p.group_name LIKE :groupName ");
            params.put("groupName", "%" + filter.getGroupName().trim() + "%");
        }

        if (filter.getFromDate() != null) {
            where.append(" AND p.created_at >= :fromDate ");
            params.put("fromDate", filter.getFromDate());
        }

        if (filter.getToDate() != null) {
            where.append(" AND p.created_at <= :toDate ");
            params.put("toDate", filter.getToDate());
        }

        return where.toString();
    }

}