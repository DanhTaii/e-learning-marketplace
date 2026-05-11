package vn.edu.nlu.fit.elearning.feature.contact.dao;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user_request.RequestFilter;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.List;


public interface ContactDao {
    int create(Contact contact);

    List<Contact> findAll();

    List<Contact> findContactsByFilter(RequestFilter filter);

    int countContactsByFilter(RequestFilter filter);

}
