package vn.edu.nlu.fit.elearning.feature.contact.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

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
}