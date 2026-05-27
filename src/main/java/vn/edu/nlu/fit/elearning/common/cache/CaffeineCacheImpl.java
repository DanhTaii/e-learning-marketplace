package vn.edu.nlu.fit.elearning.common.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheImpl implements CacheService{

    // Khởi tạo một bộ nhớ đệm Caffeine
    private final Cache<String, Object> cache;

    public CaffeineCacheImpl() {
        this.cache = Caffeine.newBuilder()
                // Cache tự động xóa sau 30 phút kể từ lúc ghi
                .expireAfterWrite(30, TimeUnit.MINUTES)
                // Chứa tối đa 1000 phần tử trong RAM, quá tải sẽ tự xóa phần tử cũ nhất
                //Cỡ đâu vài MB
                .maximumSize(1000)
                .build();
    }

    @Override
    public void put(String key, Object value) {
        if (key != null && value != null) {
            cache.put(key, value);
        }
    }

    @Override
    public Object get(String key) {
        if (key == null) {
            return null;
        }
        return cache.getIfPresent(key);
    }

    @Override
    public void invalidate(String key) {
        if (key != null) {
            cache.invalidate(key);
        }
    }

    @Override
    public void clearAll() {
        cache.invalidateAll();
    }
}
