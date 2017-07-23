package com.martingy.phonebookapp.web.rest;

import com.martingy.phonebookapp.domain.Contact;
import com.martingy.phonebookapp.service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Alert.
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactResource {

    private final ContactService service;

    public ContactResource(ContactService service) {
        this.service = service;
    }

    /**
     * GET  / : get all the contacts.
     *
     * @param pageable the pagination information
     * @param q search expression to query for in name or phone number field in contact
     * @return the ResponseEntity with status 200 (OK) and the list of contacts in body
     */
    @GetMapping
    public ResponseEntity<List<Contact>> getAll(Pageable pageable,
                                                @RequestParam(value = "q", required = false) String q) {
        Page<Contact> page = service.findAll(pageable, q);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", "" + page.getTotalElements());
        return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
    }
}
