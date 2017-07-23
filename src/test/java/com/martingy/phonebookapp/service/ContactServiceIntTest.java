package com.martingy.phonebookapp.service;

import com.martingy.phonebookapp.PhonebookApp;
import com.martingy.phonebookapp.config.Constants;
import com.martingy.phonebookapp.domain.Contact;
import com.martingy.phonebookapp.repository.ContactRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test class for the ContactService service.
 *
 * @see ContactService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhonebookApp.class)
@ActiveProfiles(Constants.SPRING_PROFILE_TEST)
@Transactional
public class ContactServiceIntTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    private Contact contact;

    private Contact getContact() {
        return new Contact("Test Contact", "+36201231234", null);
    }

    @Before
    public void beforeTest() {
        contact = getContact();
    }

    @Test
    public void testFindAllContacts() {
        testFindAllContacts(null);
    }

    private void testFindAllContacts(String q) {
        Contact _contact = contactRepository.saveAndFlush(contact);

        Page<Contact> contacts = contactService.findAll(null, q);
        Assertions.assertThat(contacts.getContent().contains(_contact));

        contactRepository.delete(_contact);
    }

    @Test
    public void testFindAllContactsWithSearchForName() {
        testFindAllContacts(contact.getName());
    }

    @Test
    public void testFindAllContactsWithSearchForPhoneNumber() {
        testFindAllContacts(contact.getPhoneNumber());
    }

    @Test
    public void testFindAllContactsWithSearchForNamePart() {
        testFindAllContacts(contact.getName().substring(0, 3));
    }

    @Test
    public void testFindAllContactsWithSearchForPhoneNumberPart() {
        testFindAllContacts(contact.getPhoneNumber().substring(0, 3));
    }
}
