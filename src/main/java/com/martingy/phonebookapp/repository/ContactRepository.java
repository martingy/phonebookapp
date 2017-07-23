package com.martingy.phonebookapp.repository;

import com.martingy.phonebookapp.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findAllByNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase(
        Pageable pageable, String name, String phoneNumber);

}
