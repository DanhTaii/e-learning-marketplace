package vn.edu.nlu.fit.elearning.feature.contact.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user_request.RequestFilter;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.List;

public interface ContactService {
    int createContact(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getContactsByFilter(RequestFilter filter);

    int countContactsByFilter(RequestFilter filter);

    Contact getContactById(int id);

    int updateContact(Contact contact);

    Contact findById(int id);

}
