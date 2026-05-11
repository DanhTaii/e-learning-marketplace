package vn.edu.nlu.fit.elearning.feature.user.admin.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user.UserFilter;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdminDaoImpl extends BaseDao implements UserAdminDao {

    public int create(User user) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO users (id, email, username, password, avatar_url, status) " +
                            "VALUES (:id, :email, :username, :password, :avatarUrl, :status)")
                    .bindBean(user)
                    .bind("status", "ACTIVE")
                    .execute();
        });
    }

    @Override
    public List<UserAdminDto> findAll() {
        String sql = """
        SELECT u.id, u.first_name AS firstName, u.last_name AS lastName, u.username,
            u.email, u.phone, u.status,
            u.avatar_url AS avatarUrl, u.created_at AS createdAt, u.updated_at AS updatedAt, r.name AS roleName
        FROM users u
        LEFT JOIN user_roles ur ON u.id = ur.user_id
        LEFT JOIN roles r ON ur.role_id = r.id
        ORDER BY u.created_at DESC
    """;
        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(UserAdminDto.class)
                        .list()
        );
    }

    public int update(User entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE users\n" +
                            "SET username = :username, avatar_url = :avatarUrl, phone = :phone, updated_at = CURRENT_TIMESTAMP\n" +
                            "WHERE id = :id")
                    .bind("phone", entity.getPhone())
                    .bind("username", entity.getUsername())
                    .bind("avatarUrl", entity.getAvatarUrl())
//                    .bind("status", entity.getStatus())
                    .bind("id", entity.getId())
                    .execute();
        });
    }

    public int delete(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM users\n" +
                    "WHERE id = :id").bind("id", integer).execute();
        });
    }

    @Override
    public int updateRole(int userId, Role role, BaseStatus status) {
        return 0;
    }

    @Override
    public List<User> findUsersByFilter(String username, String phone, String dateFrom, String role) {
        StringBuilder sql = new StringBuilder("SELECT u.id, u.username, u.email, u.phone, u.created_at AS createdAt FROM users u WHERE 1=1");

//      Phải có lúc username.trim().isEmpty() vì có thể sẽ không nhập tên nhưng để khoảng trắng thì DB nó sẽ kiếm khoảng trắng đó
        if (username != null && !username.trim().isEmpty()) {
//            String usernameSearch = "%" + username + "%";
            sql.append(" AND u.username LIKE :usernameSearch");
        }
        if (phone != null && !phone.trim().isEmpty()) {
            sql.append(" AND u.phone LIKE :phoneSearch");
        }
        if (dateFrom != null && !dateFrom.trim().isEmpty()) {
            sql.append(" AND u.created_at >= :dateFromSearch");
        }

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql.toString());
            if (username != null && !username.trim().isEmpty()) {
                String processedUsername = username.trim()
                        .replace("!", "!!")   // Thoát chính ký tự thoát trước
                        .replace("%", "!%")   // Biến % thành !%
                        .replace("_", "!_");  // Biến _ thành !_
                String usernameSearch = "%" + processedUsername.trim() + "%";
                query.bind("usernameSearch", usernameSearch);
            }
            if (phone != null && !phone.trim().isEmpty()) {
                String processedPhone = phone.trim()
                        .replace("!", "!!")   // Thoát chính ký tự thoát trước
                        .replace("%", "!%")   // Biến % thành !%
                        .replace("_", "!_");  // Biến _ thành !_

                String phoneSearch = "%" + processedPhone.trim() + "%";
                query.bind("phoneSearch", phoneSearch);
            }
            if (dateFrom != null && !dateFrom.trim().isEmpty()) {
                query.bind("dateFromSearch", dateFrom);
            }
            return query.mapToBean(User.class).list();
        });
    }

    @Override
    public List<UserAdminDto> findUsersByFilter(UserFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildUserWhereClause(filter, params);
        String sql = """
        SELECT u.id, u.first_name AS firstName, u.last_name AS lastName,
            u.username, u.email, u.phone, u.status, u.avatar_url AS avatarUrl, u.created_at AS createdAt,
            u.updated_at AS updatedAt, r.name AS roleName
        FROM users u
        LEFT JOIN user_roles ur ON u.id = ur.user_id
        LEFT JOIN roles r ON ur.role_id = r.id
    """
                + whereClause +
                """
                ORDER BY u.created_at DESC
                LIMIT :limit OFFSET :offset
                """;

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(sql);

            params.forEach(query::bind);

            query.bind("limit", filter.getSize());

            query.bind(
                    "offset",
                    (filter.getPage() - 1) * filter.getSize()
            );

            return query.mapToBean(UserAdminDto.class).list();
        });
    }

    @Override
    public int countUsersByFilter(UserFilter filter) {

        Map<String, Object> params = new HashMap<>();

        String whereClause = buildUserWhereClause(filter, params);

        String sql = """
        SELECT COUNT(DISTINCT u.id)
        FROM users u
        LEFT JOIN user_roles ur ON u.id = ur.user_id
        LEFT JOIN roles r ON ur.role_id = r.id
    """
                + whereClause;

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(sql);

            params.forEach(query::bind);

            return query.mapTo(Integer.class).one();
        });
    }

    private String buildUserWhereClause(
            UserFilter filter,
            Map<String, Object> params
    ) {

        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        // username
        if (filter.getUsername() != null &&
                !filter.getUsername().trim().isEmpty()) {

            where.append(" AND u.username LIKE :username ");

            params.put(
                    "username",
                    "%" + filter.getUsername().trim() + "%"
            );
        }

        // email
        if (filter.getEmail() != null &&
                !filter.getEmail().trim().isEmpty()) {

            where.append(" AND u.email LIKE :email ");

            params.put(
                    "email",
                    "%" + filter.getEmail().trim() + "%"
            );
        }

        // role
        if (filter.getRoleName() != null &&
                !filter.getRoleName().trim().isEmpty()) {

            where.append(" AND r.name = :roleName ");

            params.put(
                    "roleName",
                    filter.getRoleName()
            );
        }

        // status
        if (filter.getStatus() != null) {

            where.append(" AND u.status = :status ");

            params.put(
                    "status",
                    filter.getStatus().name()
            );
        }

        // from date
        if (filter.getFromDate() != null) {

            where.append(" AND u.created_at >= :fromDate ");

            params.put("fromDate", filter.getFromDate());
        }

        // to date
        if (filter.getToDate() != null) {

            where.append(" AND u.created_at <= :toDate ");

            params.put("toDate", filter.getToDate());
        }

        return where.toString();
    }

}
