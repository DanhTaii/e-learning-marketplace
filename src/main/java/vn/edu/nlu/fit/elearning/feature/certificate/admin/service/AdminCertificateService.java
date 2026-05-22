package vn.edu.nlu.fit.elearning.feature.certificate.admin.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.certificate.CertificateFilter;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateAdminDto;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateDetailAdminDto;

import java.util.List;
import java.util.Optional;

public interface AdminCertificateService {

    List<CertificateAdminDto> getCertificates(CertificateFilter filter);

    int getTotalPages(CertificateFilter filter);

    int getTotalCertificate();

    Optional<CertificateDetailAdminDto> getCertificateDetail(String code);
}
