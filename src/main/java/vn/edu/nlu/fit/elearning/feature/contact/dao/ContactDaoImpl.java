package vn.edu.nlu.fit.elearning.feature.contact.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.List;

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
}