package vn.edu.nlu.fit.elearning.feature.contact.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user_request.RequestFilter;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDaoImpl extends BaseDao implements ContactDao {

    @Override
    public int create(Contact contact) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                INSERT INTO support_requests( user_id, email, subject, message, status, tracking_token)
                VALUES ( :userId, :email, :subject, :message, :status, :trackingToken )
            """)
                        .bind("userId", contact.getUserId())
                        .bind("email", contact.getEmail())
                        .bind("subject", contact.getSubject())
                        .bind("message", contact.getMessage())
                        .bind("status", contact.getStatus().name())
                        .bind("trackingToken", contact.getTrackingToken())
                        .execute()
        );
    }

    @Override
    public List<Contact> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT sr.id, sr.email, sr.subject, sr.message, sr.status, sr.created_at\n " +
                    "FROM support_requests sr").mapToBean(Contact.class).list();
        });
    }

    @Override
    public List<Contact> findContactsByFilter(RequestFilter filter) {

        Map<String, Object> params = new HashMap<>();

        String whereClause = buildRequestWhereClause(filter, params);

        String sql = """
        SELECT sr.id,
               sr.email,
               sr.subject,
               sr.message,
               sr.status,
               sr.created_at
        FROM support_requests sr
        """
                + whereClause +
                " ORDER BY sr.created_at DESC\n" +
                "LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(sql);

            params.forEach(query::bind);

            query.bind("limit", filter.getSize());

            query.bind("offset",
                    (filter.getPage() - 1) * filter.getSize());

            return query.mapToBean(Contact.class).list();
        });
    }

    private String buildRequestWhereClause(
            RequestFilter filter,
            Map<String, Object> params
    ) {

        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        // email
        if (filter.getEmail() != null &&
                !filter.getEmail().trim().isEmpty()) {

            where.append(" AND sr.email LIKE :email ");

            params.put(
                    "email",
                    "%" + filter.getEmail().trim() + "%"
            );
        }

        // subject
        if (filter.getSubject() != null &&
                !filter.getSubject().trim().isEmpty()) {

            where.append(" AND sr.subject LIKE :subject ");

            params.put(
                    "subject",
                    "%" + filter.getSubject().trim() + "%"
            );
        }

        // from date
        if (filter.getFromDate() != null) {

            where.append(" AND sr.created_at >= :fromDate ");

            params.put("fromDate", filter.getFromDate());
        }

        // to date
        if (filter.getToDate() != null) {

            where.append(" AND sr.created_at <= :toDate ");

            params.put("toDate", filter.getToDate());
        }

        // status
        if (filter.getStatus() != null) {

            where.append(" AND sr.status = :status ");

            params.put(
                    "status",
                    filter.getStatus().name()
            );
        }

        return where.toString();
    }

    @Override
    public int countContactsByFilter(RequestFilter filter) {

        Map<String, Object> params = new HashMap<>();

        String whereClause = buildRequestWhereClause(filter, params);

        String sql = """
        SELECT COUNT(*)
        FROM support_requests sr
        """
                + whereClause;

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(sql);

            params.forEach(query::bind);

            return query.mapTo(Integer.class).one();
        });
    }

    @Override
    public Contact findById(Integer id) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                SELECT sr.id, sr.user_id, sr.email, sr.subject, sr.message, sr.status, sr.admin_reply, sr.tracking_token, sr.created_at, sr.updated_at, sr.resolved_at
                FROM support_requests sr
                WHERE sr.id = :id
                """)
                        .bind("id", id)
                        .mapToBean(Contact.class)
                        .findFirst()
                        .orElse(null)
        );
    }


}