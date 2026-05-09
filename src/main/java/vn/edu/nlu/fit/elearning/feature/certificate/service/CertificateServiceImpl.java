package vn.edu.nlu.fit.elearning.feature.certificate.service;

import vn.edu.nlu.fit.elearning.feature.certificate.dao.CertificateDao;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;

import java.time.Year;
import java.util.UUID;

public class CertificateServiceImpl implements CertificateService {

    private final CertificateDao certificateDao;

    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public int createCertificate(Certificate cert) {
        String code = generateUniqueCertificateCode(cert.getUserId(), cert.getCourseId());
        cert.setCertificateCode(code);
        return certificateDao.create(cert);
    }

    @Override
    public boolean hasCertificate(int userId, int courseId) {
        return certificateDao.hasCertificate(userId, courseId);
    }

    @Override
    public String generateUniqueCertificateCode(int userId, int courseId) {
        //Lấy ngẫu nhiên 5 kí tự
        String randomPart = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        int currentYear = Year.now().getValue();

        return String.format("CERT-C%d-U%d-%s-%d", courseId, userId, randomPart, currentYear);
    }
}
