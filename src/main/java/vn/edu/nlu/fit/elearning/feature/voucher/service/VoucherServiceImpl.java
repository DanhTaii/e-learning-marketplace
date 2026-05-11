package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.voucher.dao.VoucherDao;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public class VoucherServiceImpl implements VoucherService {

    private VoucherDao voucherDao;

    public VoucherServiceImpl(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }


    @Override
    public List<Voucher> findAll() {
        return List.of();
    }

    @Override
    public List<Voucher> findValidVouchers() {
        return voucherDao.findValidVouchers();
    }

    @Override
    public Voucher findByCode(String code) {
        return null;
    }
}
