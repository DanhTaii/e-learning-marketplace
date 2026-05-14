package vn.edu.nlu.fit.elearning.feature.certificate.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.certificate.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;

import java.util.List;

public class CertificateDaoImp extends BaseDao implements CertificateDao {
    @Override
    public int create(Certificate entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO certificates (user_id, course_id, certificate_code, pdf_url) " +
                            "VALUES (:userId, :courseId, :certificateCode, :pdfUrl)")
                    .bindBean(entity)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public Certificate findById(Integer integer) {
        return null;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public int update(Certificate entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public boolean hasCertificate(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT EXISTS (SELECT 1 FROM certificates WHERE user_id = :userId AND course_id = :courseId)")
                    .bind("userId", userId)
                    .bind("courseId", courseId)
                    .mapTo(Boolean.class)
                    .one();
        });
    }

    @Override
    public CertificateDetailDto findByUserIdAndCourseId(int userId, int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT cert.id AS id, c.id AS courseId, c.title AS courseTitle, u.first_name, u.last_name, " +
                            "(SELECT IFNULL(SUM(l.duration_minutes), 0) / 60 FROM lessons l WHERE l.course_id = c.id) AS durationHours, " +
                            "cert.certificate_code, cert.issue_date, cert.pdf_url " +
                            "FROM certificates cert " +
                            "JOIN courses c ON cert.course_id = c.id " +
                            "JOIN users u ON u.id = cert.user_id " +
                            "WHERE cert.user_id = :userId AND cert.course_id = :courseId")
                    .bind("userId", userId)
                    .bind("courseId", courseId)
                    .mapToBean(CertificateDetailDto.class)
                    .findFirst().orElse(null);
        });
    }

}
