package vn.edu.nlu.fit.elearning.feature.voucher.dao;

import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public interface VoucherDao {
    List<Voucher> findAll();
    List<Voucher> findValidVouchers();
    Voucher findByCode(String code);

    void increaseUsedCount(Integer voucherId);

    boolean hasUserUsedVoucher(Integer userId, Integer voucherId);
}