package com.martingy.phonebookapp.web.rest;

import com.martingy.phonebookapp.PhonebookApp;
import com.martingy.phonebookapp.config.Constants;
import com.martingy.phonebookapp.domain.Contact;
import com.martingy.phonebookapp.repository.ContactRepository;
import com.martingy.phonebookapp.service.ContactService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Test class for the ContactService resource.
 *
 * @see ContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhonebookApp.class)
@ActiveProfiles(Constants.SPRING_PROFILE_TEST)
@Transactional
public class ContactResourceIntTest {

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    private MockMvc restMockMvc;

    private Contact contact;

    @Before
    public void setup() {
        ContactResource contactResource = new ContactResource(contactService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(contactResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter)
            .build();
    }

    private Contact getContact() {
        return new Contact("Test Contact", "+36201231234", null);
    }

    @Before
    public void beforeTest() {
        contact = getContact();
    }

    @Test
    public void testGetAllContacts() throws Exception {
        Contact _contact = contactRepository.saveAndFlush(contact);

        restMockMvc.perform(MockMvcRequestBuilders.get("/api/contacts"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id")
                .value(Matchers.hasItem(_contact.getId().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name")
                .value(Matchers.hasItem(_contact.getName())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].phoneNumber")
                .value(Matchers.hasItem(_contact.getPhoneNumber())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].picture")
                .value(Matchers.hasItem(_contact.getPicture())));

        contactRepository.delete(_contact);
    }

    private void testGetAllContacts(String q) throws Exception {
        Contact _contact = contactRepository.saveAndFlush(contact);

        MockHttpServletRequestBuilder contactsApiRequestBuilder = MockMvcRequestBuilders.get("/api/contacts");
        if (!StringUtils.isEmpty(q)) {
            contactsApiRequestBuilder.param("q", q);
        }
        restMockMvc.perform(contactsApiRequestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id")
                .value(Matchers.hasItem(_contact.getId().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name")
                .value(Matchers.hasItem(_contact.getName())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].phoneNumber")
                .value(Matchers.hasItem(_contact.getPhoneNumber())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[*].picture")
                .value(Matchers.hasItem(_contact.getPicture())));

        contactRepository.delete(_contact);
    }

    @Test
    public void testGetAllContactsWithSearchForName() throws Exception {
        testGetAllContacts(contact.getName());
    }

    @Test
    public void testGetAllContactsWithSearchForPhoneNumber() throws Exception {
        testGetAllContacts(contact.getPhoneNumber());
    }

    @Test
    public void testGetAllContactsWithSearchForNamePart() throws Exception {
        testGetAllContacts(contact.getName().substring(0, 3));
    }

    @Test
    public void testGetAllContactsWithSearchForPhoneNumberPart() throws Exception {
        testGetAllContacts(contact.getPhoneNumber().substring(0, 3));
    }

}
