package vn.edu.nlu.fit.elearning.feature.certificate.admin.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.certificate.CertificateFilter;
import vn.edu.nlu.fit.elearning.common.utils.StringUtils;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateAdminDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminCertificateDaoImp extends BaseDao implements AdminCertificateDao {

    private final String baseSql = "SELECT cert.id, cert.certificate_code as certificateCode, " +
            "c.title as courseTitle, " +
            "CONCAT(u.first_name, ' ', u.last_name) as username, " +
            "cert.issue_date as issueDate, cert.status, cert.pdf_url as pdfUrl " +
            "FROM certificates cert " +
            "JOIN courses c ON cert.course_id = c.id " +
            "JOIN users u ON cert.user_id = u.id ";

    @Override
    public List<CertificateAdminDto> findByFilter(CertificateFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(filter, params);

        String finalSql = baseSql + whereClause + " ORDER BY cert.issue_date DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(finalSql);
            params.forEach(query::bind);
            query.bind("limit", filter.getLimit());
            query.bind("offset", filter.getOffSet());

            return query.mapToBean(CertificateAdminDto.class).list();
        });
    }

    @Override
    public int countByFilter(CertificateFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildWhereClause(filter, params);

        // Cần join bảng để có thể đếm khi tìm kiếm theo tên khóa học hoặc tên học viên
        String finalSql = "SELECT COUNT(cert.id) FROM certificates cert " +
                "JOIN courses c ON cert.course_id = c.id " +
                "JOIN users u ON cert.user_id = u.id " + whereClause;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(finalSql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    @Override
    public int countTotal() {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT COUNT(id) FROM certificates";
            return handle.createQuery(sql).mapTo(Integer.class).one();
        });
    }

    private String buildWhereClause(CertificateFilter filter, Map<String, Object> params) {
        StringBuilder conditionalSentence = new StringBuilder(" WHERE 1=1");

        // Tìm kiếm theo mã chứng chỉ hoặc tên học viên
        if (filter.getSearchName() != null && !filter.getSearchName().trim().isEmpty()) {
            conditionalSentence.append(" AND (cert.certificate_code LIKE :search OR CONCAT(u.first_name, ' ', u.last_name) LIKE :search)");
            params.put("search", "%" + StringUtils.escapeLikeWildcards(filter.getSearchName().trim()) + "%");
        }

        // Lọc theo trạng thái
        if (filter.getStatus() != null) {
            conditionalSentence.append(" AND cert.status = :status");
            params.put("status", filter.getStatus());
        }

        // Lọc theo khóa học
        if (filter.getCourseId() != null && filter.getCourseId() > 0) {
            conditionalSentence.append(" AND cert.course_id = :courseId");
            params.put("courseId", filter.getCourseId());
        }

        // Lọc từ ngày
        if (filter.getFromDate() != null) {
            conditionalSentence.append(" AND DATE(cert.issue_date) >= :fromDate");
            params.put("fromDate", filter.getFromDate());
        }

        // Lọc đến ngày
        if (filter.getToDate() != null) {
            conditionalSentence.append(" AND DATE(cert.issue_date) <= :toDate");
            params.put("toDate", filter.getToDate());
        }

        return conditionalSentence.toString();
    }


}
