package vn.edu.nlu.fit.elearning.feature.contact.service;

import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.contact.model.Contact;

import java.util.List;

public interface ContactService {
    int createContact(Contact contact);

    List<Contact> getAllContacts();
}
