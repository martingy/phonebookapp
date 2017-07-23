package com.martingy.phonebookapp.service;

import com.martingy.phonebookapp.domain.Contact;
import com.martingy.phonebookapp.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Service Implementation for Contact entity.
 */
@Service
@Transactional
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    /**
     *  Get all the contacts
     *
     *  @param pageable the pagination information
     *  @param q search expression to query for in name or phone number field in contact
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Contact> findAll(Pageable pageable, String q) {
        return StringUtils.isEmpty(q) ?
            repository.findAll(pageable) :
            repository.findAllByNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase(pageable, q, q);
    }
}
