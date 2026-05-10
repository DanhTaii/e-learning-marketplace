package vn.edu.nlu.fit.elearning.feature.contact.dao;

import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.List;


public interface ContactDao {
    int create(Contact contact);

    List<Contact> findAll();

}
