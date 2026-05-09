package vn.edu.nlu.fit.elearning.feature.voucher.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    "WHERE is_active = 1 " +
                    "AND end_date >= NOW() " + // Chưa hết hạn
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

}
