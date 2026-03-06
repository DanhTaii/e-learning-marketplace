package vn.edu.nlu.fit.elearning.feature.user.model; // (Giả định package của bạn)

import vn.edu.nlu.fit.elearning.helper.enums.BasicStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String role;
    private BasicStatus status;
    private String avatarUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String username, String email, String password, String phone, String role, BasicStatus status, String avatarUrl, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 3. GETTERS VÀ SETTERS (BẮT BUỘC CHO JDBI/BEAN MAPPER)

    // Lưu ý: Các thuộc tính snake_case trong DB (ví dụ: first_name, avatar_url)
    // đã được chuyển sang camelCase trong Java (ví dụ: firstName, avatarUrl)
    // để JDBI có thể ánh xạ tự động.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BasicStatus getStatus() {
        return status;
    }

    public void setStatus(BasicStatus status) {
        this.status = status;
    }

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