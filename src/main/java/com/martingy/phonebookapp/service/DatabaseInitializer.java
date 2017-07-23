package com.martingy.phonebookapp.service;

import com.martingy.phonebookapp.config.Constants;
import com.martingy.phonebookapp.domain.Contact;
import com.martingy.phonebookapp.repository.ContactRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Profile("!" + Constants.SPRING_PROFILE_TEST)
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final ContactRepository contactRepository;

    public DatabaseInitializer(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    @Override
    public void run(String... strings) throws Exception {
        log.info("Running Database initialization");
        contactRepository.save(
            new Contact("Test Elek", "+36201231234",
                getPicture("icon-ios7-contact-128.png")));
        contactRepository.save(
            new Contact("Sample John", "+36204564567",
                getPicture("icon-ios7-contact-outline-128.png")));
        log.info("Database initialization complete");
    }

    private byte[] getPicture(String fileName) {
        try {
            Resource resource = new ClassPathResource(fileName);
            return IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
