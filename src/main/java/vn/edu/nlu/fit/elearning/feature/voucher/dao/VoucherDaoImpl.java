package vn.edu.nlu.fit.elearning.feature.voucher.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public class VoucherDaoImpl extends BaseDao implements VoucherDao {
    public List<Voucher> findAll() {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT * FROM vouchers ORDER BY created_at DESC";

            return handle.createQuery(sql)
                    .mapToBean(Voucher.class)
                    .list();
        });
    }

    public List<Voucher> findValidVouchers() {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT * FROM vouchers " +
                    "WHERE status = 'ACTIVE' " +
                    "AND end_date >= NOW() " +
                    "AND (usage_limit IS NULL OR used_count < usage_limit) " +
                    "ORDER BY created_at DESC";

            return handle.createQuery(sql)
                    .mapToBean(Voucher.class)
                    .list();
        });
    }

    public Voucher findByCode(String code) {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT * FROM vouchers WHERE code = :code LIMIT 1";

            return handle.createQuery(sql)
                    .bind("code", code)
                    .mapToBean(Voucher.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    // tăng lượt sử dụng voucher lên 1 khi người dùng sử dụng voucher
    @Override
    public void increaseUsedCount(Integer voucherId) {
        getJdbi().useHandle(handle -> {
            String sql = "UPDATE vouchers SET used_count = used_count + 1 WHERE id = :voucherId";
            handle.createUpdate(sql)
                    .bind("voucherId", voucherId)
                    .execute();
        });
    }

    //hàm check voucher trong order có không để ngăn người dùng spam voucher
    @Override
    public boolean hasUserUsedVoucher(Integer userId, Integer voucherId) {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT COUNT(id) FROM orders " +
                    "WHERE user_id = :userId " +
                    "AND voucher_id = :voucherId " +
                    "AND status = 'PAID'";

            int count = handle.createQuery(sql)
                    .bind("userId", userId)
                    .bind("voucherId", voucherId)
                    .mapTo(Integer.class)
                    .one();

            return count > 0;
        });
    }
}
