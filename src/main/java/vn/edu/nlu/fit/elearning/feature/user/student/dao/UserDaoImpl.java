package vn.edu.nlu.fit.elearning.feature.user.student.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User findById(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("select u.id, u.username, u.first_name AS firstName, u.last_name AS lastName, u.avatar_url, u.email, u.phone, u.role, u.status, u.created_at AS createdAt, u.updated_at AS updatedAt " +
                            "FROM users u where u.id = :id")
                    .bind("id", integer)
                    .mapToBean(User.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public User findUserByEmail(String email) {
        return getJdbi().withHandle(handle -> {
//            Với hàm cũ là :handle.createQuery("select * from users u where u.email = :email").bind("email", email).mapToBean(User.class).one();
//            Sẽ lỗi do nếu email KHÔNG tồn tại (không có kết quả nào),
//            .one() sẽ ném ra ngoại lệ IllegalStateException: Expected one element, but found none,
//            dẫn đến lỗi HTTP 500.
            return handle.createQuery("select * from users u where u.email = :email")
                    .bind("email", email.trim())
                    .mapToBean(User.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    public User findByProviderAndProviderId(String provider, String providerId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE provider = :provider AND provider_id = :providerId")
                        .bind("provider", provider)
                        .bind("providerId", providerId)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
    }

    @Override
    public boolean findUserByUsername(String username) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("select 1 from users u where u.username = :username")
                    .bind("username", username)
                    .mapTo(Integer.class)
                    .findOne()
                    .isPresent();

        });
    }

    @Override
    public int resetPassword(String newPassword, String userMail) {
//        Do là với withHandle thì nó sẽ trả về kiểu dữ liệu và có đủ CRUD nene có thể return về chính nó luôn
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(
                            "UPDATE users\n" +
                                    "SET password =  :newPassword, updated_at = CURRENT_TIMESTAMP\n" +
                                    "WHERE email = :userMail")
                    .bind("userMail", userMail)
                    .bind("newPassword", newPassword)
                    .execute();
        });
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT COUNT(id) FROM users WHERE email = :email")
                    .bind("email", email)
                    .mapTo(Integer.class)
                    .one() > 0;
        });
    }

    @Override
    public int countUsersByTimeRange(String timeRange) {
        String timeCondition = buildTimeCondition(timeRange, "created_at");
        String sql = "SELECT COUNT(id) FROM users WHERE " + timeCondition;

        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapTo(Integer.class)
                    .findFirst()
                    .orElse(0);
        });
    }

    @Override
    public Set<String> findRolesByUserId(Integer userId) {
        return getJdbi().withHandle(handle ->
                new HashSet<>(handle.createQuery(
                                "SELECT DISTINCT r.name " +
                                        "FROM users u " +
                                        "JOIN user_roles ur ON u.id = ur.user_id " +
                                        "JOIN roles r ON ur.role_id = r.id " +
                                        "WHERE u.id = :userId")
                        .bind("userId", userId)
                        .mapTo(String.class)
                        .list())
        );
    }


    @Override
    public Set<String> findPermissionsByUserId(Integer userId) {
        return getJdbi().withHandle(handle ->
                new HashSet<>(handle.createQuery(
                                "SELECT DISTINCT p.name " +
                                        "FROM users u " +
                                        "JOIN user_roles ur ON u.id = ur.user_id " +
                                        "JOIN role_permissions rp ON ur.role_id = rp.role_id " +
                                        "JOIN permissions p ON rp.permission_id = p.id " +
                                        "WHERE u.id = :userId")
                        .bind("userId", userId)
                        .mapTo(String.class)
                        .list())
        );
    }

    @Override
    public int increaseFailedAttempts(String email) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                                    UPDATE users
                                    SET failed_attempts = failed_attempts + 1,
                                        updated_at = CURRENT_TIMESTAMP
                                    WHERE email = :email
                                """)
                        .bind("email", email)
                        .execute()
        );
    }

    @Override
    public int resetFailedAttempts(String email) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                                    UPDATE users
                                    SET failed_attempts = 0,
                                        updated_at = CURRENT_TIMESTAMP
                                    WHERE email = :email
                                """)
                        .bind("email", email)
                        .execute()
        );
    }

    @Override
    public int lockUserAccount(String email) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                                    UPDATE users
                                    SET status = 'INACTIVE',
                                        updated_at = CURRENT_TIMESTAMP
                                    WHERE email = :email
                                """)
                        .bind("email", email)
                        .execute()
        );
    }

    @Override
    public int getFailedAttemptsByEmail(String email) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                                    SELECT failed_attempts
                                    FROM users
                                    WHERE email = :email
                                """)
                        .bind("email", email)
                        .mapTo(Integer.class)
                        .findFirst()
                        .orElse(0)
        );
    }

    @Override
    public int updateAvatar(int userId, String avatarUrl) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                                UPDATE users
                                SET avatar_url = :avatarUrl,
                                    updated_at = CURRENT_TIMESTAMP
                                WHERE id = :userId
                                """)
                        .bind("avatarUrl", avatarUrl)
                        .bind("userId", userId)
                        .execute()
        );
    }

    @Override
    public User findByUsername(String username) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                    SELECT *
                    FROM users
                    WHERE username = :username
                    """)
                        .bind("username", username)
                        .mapToBean(User.class)
                        .findFirst()
                        .orElse(null)
        );
    }
    @Override
    public int updateProfile(User user) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                    UPDATE users
                    SET first_name = :firstName,
                        last_name = :lastName,
                        username = :username,
                        email = :email,
                        phone = :phone,
                        updated_at = CURRENT_TIMESTAMP
                    WHERE id = :id
                    """)
                        .bindBean(user)
                        .execute()
        );
    }

    @Override
    public int createRegisteredUser(User user) {

        return getJdbi().inTransaction(handle -> {

            Integer userId = handle.createUpdate("""
                INSERT INTO users ( email, first_name, last_name, username, password, avatar_url, status)
                VALUES (:email, :firstName, :lastName, :username, :password, :avatarUrl, :status)
                """)
                    .bindBean(user)
                    .bind("status", "ACTIVE")
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();

            handle.createUpdate("""
                INSERT INTO user_roles (user_id, role_id)
                VALUES (:userId,:roleId)
                """)
                    .bind("userId", userId)
                    .bind("roleId", 5)
                    .execute();

            return userId;
        });
    }


}
