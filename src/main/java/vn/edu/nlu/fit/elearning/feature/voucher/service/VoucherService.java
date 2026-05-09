package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public interface VoucherService {
    List<Voucher> findAll();
    List<Voucher> findValidVouchers();
    Voucher findByCode(String code);
}
