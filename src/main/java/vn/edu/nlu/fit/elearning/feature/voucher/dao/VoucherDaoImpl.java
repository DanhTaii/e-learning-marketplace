package vn.edu.nlu.fit.elearning.feature.voucher.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherArchiveFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoucherDaoImpl extends BaseDao implements VoucherDao {

    @Override
    public Voucher findById(Integer id) {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT * FROM vouchers WHERE id = :id";
            return handle.createQuery(sql)
                    .bind("id", id)
                    .mapToBean(Voucher.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public int create(Voucher entity) {
        String sql = "INSERT INTO vouchers (code, title, description, discount_type, discount_value, " +
                "min_order_value, max_discount_value, usage_limit, used_count, start_date, end_date, status) " +
                "VALUES (:code, :title, :description, :discountType, :discountValue, " +
                ":minOrderValue, :maxDiscountValue, :usageLimit, :usedCount, :startDate, :endDate, :status)";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }
    @Override
    public boolean update(Voucher entity) {
        String sql = "UPDATE vouchers SET " +
                "code = :code, " +
                "title = :title, " +
                "description = :description, " +
                "discount_type = :discountType, " +
                "discount_value = :discountValue, " +
                "min_order_value = :minOrderValue, " +
                "max_discount_value = :maxDiscountValue, " +
                "usage_limit = :usageLimit, " +
                "start_date = :startDate, " +
                "end_date = :endDate, " +
                "status = :status, " +
                "updated_at = NOW() " + // Cập nhật lại thời gian sửa đổi
                "WHERE id = :id";

        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .execute() > 0;
        });
    }



    @Override
    public List<Voucher> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM vouchers WHERE is_deleted = 0 ORDER BY created_at DESC")
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
        where.append(" AND v.is_deleted = 0");
        return where.toString();
    }
    @Override
    public int changeVouchersStatusByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return 0;

        return getJdbi().withHandle(handle -> {
            String sql = "UPDATE vouchers " +
                    "SET status = CASE WHEN status = 'ACTIVE' THEN 'INACTIVE' ELSE 'ACTIVE' END " +
                    "WHERE id IN (<ids>)";
            return handle.createUpdate(sql)
                    .bindList("ids", ids)
                    .execute();
        });
    }

    @Override
    public int archiveVouchersByIds(List<Integer> ids, String deleteReason) {
        if (ids == null || ids.isEmpty()) return 0;

        return getJdbi().withHandle(handle -> {
            // Lưu ý: Logic này giả định bảng vouchers của bạn có các cột: is_deleted, deleted_at, delete_reason tương tự như bảng lessons
            String sql = "UPDATE vouchers " +
                    "SET deleted_at = CASE WHEN is_deleted = 0 THEN NOW() ELSE NULL END, " +
                    "is_deleted = 1 - is_deleted, " +
                    "delete_reason = :deleteReason, " +
                    "status = 'INACTIVE' " +
                    "WHERE id IN (<ids>)";

            return handle.createUpdate(sql)
                    .bindList("ids", ids)
                    .bind("deleteReason", deleteReason)
                    .execute();
        });
    }

    @Override
    public int deleteVouchersByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return 0;

        return getJdbi().withHandle(handle -> {
            String sql = "DELETE FROM vouchers WHERE id IN (<ids>)";
            return handle.createUpdate(sql)
                    .bindList("ids", ids)
                    .execute();
        });
    }

    @Override
    public int countAllVouchersArchive() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT COUNT(*) FROM vouchers WHERE is_deleted = 1")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public List<Voucher> findArchivedVouchersByFilter(VoucherArchiveFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildArchivedVoucherWhereClause(filter, params);

        String sql = "SELECT v.id, v.code, v.title, v.description, v.discount_type, v.discount_value, " +
                "v.min_order_value, v.max_discount_value, v.usage_limit, v.used_count, " +
                "v.start_date, v.end_date, v.status, v.created_at, v.updated_at, v.deleted_at, v.delete_reason " +
                "FROM vouchers v "
                + whereClause
                + " ORDER BY v.deleted_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());
            return query.mapToBean(Voucher.class).list();
        });
    }

    @Override
    public int countVouchersArchiveByFilter(VoucherArchiveFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildArchivedVoucherWhereClause(filter, params);

        String sql = "SELECT COUNT(*) FROM vouchers v" + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildArchivedVoucherWhereClause(VoucherArchiveFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            where.append(" AND (v.title LIKE :nameSearch OR v.code LIKE :nameSearch)");
            params.put("nameSearch", "%" + filter.getName().trim() + "%");
        }

        if (filter.getDeletedFromDate() != null) {
            where.append(" AND v.deleted_at >= :fromDate");
            params.put("fromDate", filter.getDeletedFromDate());
        }

        if (filter.getDeletedToDate() != null) {
            where.append(" AND v.deleted_at <= :toDate");
            params.put("toDate", filter.getDeletedToDate());
        }

        where.append(" AND v.is_deleted = 1");

        return where.toString();
    }

    @Override
    public int restoreVouchersByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return 0;

        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE vouchers SET is_deleted = 0 WHERE id IN (<ids>)")
                    .bindList("ids", ids)
                    .execute();
        });
    }
}