package vn.edu.nlu.fit.elearning.feature.voucher.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherArchiveFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public interface VoucherDao {
    Voucher findById(Integer id);

    int create(Voucher entity);

    boolean update(Voucher entity);


    List<Voucher> findAll();
    List<Voucher> findValidVouchers();
    Voucher findByCode(String code);

    void increaseUsedCount(Integer voucherId);

    boolean hasUserUsedVoucher(Integer userId, Integer voucherId);

    List<Voucher> getVoucherBySearch(VoucherFilter filter);

    int countVouchersByFilter(VoucherFilter filter);

    int changeVouchersStatusByIds(List<Integer> ids);

    int archiveVouchersByIds(List<Integer> ids, String deleteReason);

    int deleteVouchersByIds(List<Integer> ids);

    int countAllVouchersArchive();

    List<Voucher> findArchivedVouchersByFilter(VoucherArchiveFilter filter);

    int countVouchersArchiveByFilter(VoucherArchiveFilter filter);

    int restoreVouchersByIds(List<Integer> ids);
}