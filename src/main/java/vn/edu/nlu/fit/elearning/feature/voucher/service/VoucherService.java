package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.voucher.dto.VoucherResultDTO;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public interface VoucherService {
    List<Voucher> findAll();
    List<Voucher> findValidVouchers();
    Voucher findByCode(String code);
    VoucherResultDTO applyVoucher(Integer userId, String code, double cartTotal) throws Exception;

    void increaseUsedCount(int voucherId);

    boolean hasUserUsedVoucher(Integer userId, Integer id);

    List<Voucher> searchVouchers(VoucherFilter filter);

    int getCountVouchersByFilter(VoucherFilter filter);
}
