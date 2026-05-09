package vn.edu.nlu.fit.elearning.feature.certificate.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;

public interface CertificateDao extends BaseCrudDao<Certificate, Integer> {

    boolean hasCertificate(int userId, int courseId);

}
