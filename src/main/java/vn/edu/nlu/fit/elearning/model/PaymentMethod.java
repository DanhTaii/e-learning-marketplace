package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;

public class PaymentMethod implements Serializable {
    private int id;
    private String name;    // Ví dụ: "Ví Momo"
    private String code;    // Ví dụ: "MOMO", "VNPAY", "BANK_TRANSFER"
    private String iconUrl;
    private boolean active; // Map từ is_active (Có đang bật hay tắt phương thức này không)

    // 1. Constructor rỗng
    public PaymentMethod() {
    }

    // 2. Constructor đầy đủ
    public PaymentMethod(int id, String name, String code, String iconUrl, boolean active) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.iconUrl = iconUrl;
        this.active = active;
    }

    // --- GETTER & SETTER ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }


}