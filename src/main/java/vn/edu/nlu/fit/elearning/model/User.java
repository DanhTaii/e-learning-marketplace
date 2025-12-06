package vn.edu.nlu.fit.elearning.model; // (Giả định package của bạn)

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

    // id INT AUTO_INCREMENT PRIMARY KEY
    private Integer id;

    // first_name VARCHAR(30) NOT NULL
    private String firstName;

    // last_name VARCHAR(30) NOT NULL
    private String lastName;

    // username VARCHAR(100)
    private String username; // Có thể NULL

    // email VARCHAR(250) NOT NULL UNIQUE
    private String email;

    // password VARCHAR(250) NOT NULL
    private String password;

    // phone VARCHAR(20)
    private String phone; // Có thể NULL

    // role ENUM('user','admin') DEFAULT 'user'
    // Giữ là String để dễ dàng map với JDBI, hoặc có thể dùng Enum riêng.
    private String role;

    // avatar_url VARCHAR(500)
    private String avatarUrl; // Có thể NULL

    // created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    private LocalDateTime createdAt;

    // updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    private LocalDateTime updatedAt;

    // 1. CONSTRUCTOR RỖNG (BẮT BUỘC CHO JDBI/BEAN MAPPER)
    public User() {
    }

    // 2. CONSTRUCTOR ĐẦY ĐỦ (Tùy chọn, dùng để tạo đối tượng khi lấy từ DB)
    public User(Integer id, String firstName, String lastName, String username, String email, String password, String phone, String role, String avatarUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 3. GETTERS VÀ SETTERS (BẮT BUỘC CHO JDBI/BEAN MAPPER)

    // Lưu ý: Các thuộc tính snake_case trong DB (ví dụ: first_name, avatar_url)
    // đã được chuyển sang camelCase trong Java (ví dụ: firstName, avatarUrl)
    // để JDBI có thể ánh xạ tự động.

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // 4. TOSTRING()
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}