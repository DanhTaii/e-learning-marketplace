package vn.edu.nlu.fit.elearning.feature.contact.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.ContactStatus;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.dao.ContactDao;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public int createContact(Contact contact) {
        if (contact != null) {
            contact.setTrackingToken(java.util.UUID.randomUUID().toString());
            if (contact.getStatus() == null) {
                contact.setStatus(ContactStatus.PENDING);
            }
            return contactDao.create(contact);
        }
        return 0;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.findAll();
    }


}