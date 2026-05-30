package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherArchiveFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.voucher.dto.VoucherResultDTO;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public interface VoucherService {
    Voucher findById(Integer id);

    List<Voucher> findAll();
    List<Voucher> findValidVouchers();
    Voucher findByCode(String code);
    VoucherResultDTO applyVoucher(Integer userId, String code, double cartTotal) throws Exception;

    void increaseUsedCount(int voucherId);

    boolean hasUserUsedVoucher(Integer userId, Integer id);

    List<Voucher> searchVouchers(VoucherFilter filter);

    int getCountVouchersByFilter(VoucherFilter filter);

    int changeVouchersStatusByIds(List<Integer> ids);

    int archiveVouchersByIds(List<Integer> ids, String deleteReason);

    int deleteVouchersByIds(List<Integer> ids);

    int bulkDuplicateVouchers(List<Integer> ids);

    int getTotalVouchersArchive();

    List<Voucher> getArchivedVouchersByFilter(VoucherArchiveFilter filter);

    int getCountVouchersArchiveByFilter(VoucherArchiveFilter filter);

    int restoreVouchersByIds(List<Integer> ids);

    int createVoucher(Voucher voucher);

    boolean updateVoucher(Voucher voucher);

    boolean checkVoucherCode(String code);
}
