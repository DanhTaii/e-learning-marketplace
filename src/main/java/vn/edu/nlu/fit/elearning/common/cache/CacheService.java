package vn.edu.nlu.fit.elearning.common.cache;

public interface CacheService {
    void put(String key, Object value);

    Object get(String key);

    // Hàm get nâng cao, tự động ép kiểu theo Class truyền vào
    <T> T get(String key, Class<T> clazz);

    // Xóa 1 cache cụ thể khi dữ liệu thay đổi
    void invalidate(String key);

    // Xóa sạch bách cache khi có thay đổi lớn hoặc theo lịch trình
    void clearAll();
}
