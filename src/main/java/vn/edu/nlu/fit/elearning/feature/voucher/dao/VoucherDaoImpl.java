package vn.edu.nlu.fit.elearning.feature.voucher.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoucherDaoImpl extends BaseDao implements VoucherDao {
    @Override
    public List<Voucher> findAll() {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT * FROM vouchers ORDER BY created_at DESC";
            return handle.createQuery(sql)
                    .mapToBean(Voucher.class)
                    .list();
        });
    }

    @Override
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

    @Override
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

    @Override
    public void increaseUsedCount(Integer voucherId) {
        getJdbi().useHandle(handle -> {
            String sql = "UPDATE vouchers SET used_count = used_count + 1 WHERE id = :voucherId";
            handle.createUpdate(sql)
                    .bind("voucherId", voucherId)
                    .execute();
        });
    }

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

    @Override
    public List<Voucher> getVoucherBySearch(VoucherFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildVoucherWhereClause(filter, params);

        // Đảm bảo lấy đầy đủ các trường khớp với Model Voucher
        String sql = "SELECT v.id, v.code, v.title, v.description, v.discount_type, v.discount_value, " +
                "v.min_order_value, v.max_discount_value, v.usage_limit, v.used_count, " +
                "v.start_date, v.end_date, v.status, v.created_at, v.updated_at " +
                "FROM vouchers v "
                + whereClause
                + " ORDER BY v.created_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());
            return query.mapToBean(Voucher.class).list();
        });
    }

    @Override
    public int countVouchersByFilter(VoucherFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildVoucherWhereClause(filter, params);

        String sql = "SELECT COUNT(*) FROM vouchers v" + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildVoucherWhereClause(VoucherFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            where.append(" AND (v.title LIKE :keywordSearch OR v.code LIKE :keywordSearch)");
            params.put("keywordSearch", "%" + filter.getName().trim() + "%");
        }

        if (filter.getFromDate() != null) {
            where.append(" AND v.created_at >= :fromDate");
            params.put("fromDate", filter.getFromDate());
        }
        if (filter.getToDate() != null) {
            where.append(" AND v.created_at <= :toDate");
            params.put("toDate", filter.getToDate());
        }

        // Đã sửa: Lấy .name() của Enum để so sánh chuỗi dưới Database chính xác
        if (filter.getStatus() != null) {
            where.append(" AND v.status = :status");
            params.put("status", filter.getStatus().name());
        }

        if (filter.getDiscountType() != null && !filter.getDiscountType().trim().isEmpty()) {
            where.append(" AND v.discount_type = :discountType");
            params.put("discountType", filter.getDiscountType());
        }

        if (filter.getExpiredSoon() != null && filter.getExpiredSoon()) {
            where.append(" AND v.end_date >= NOW() AND v.end_date <= DATE_ADD(NOW(), INTERVAL 3 DAY)");
        }

        return where.toString();
    }
}