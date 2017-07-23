package com.martingy.phonebookapp.service;

import com.martingy.phonebookapp.PhonebookApp;
import com.martingy.phonebookapp.config.Constants;
import com.martingy.phonebookapp.repository.ContactRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for the DatabaseInitializer.
 *
 * @see DatabaseInitializer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhonebookApp.class)
@ActiveProfiles(Constants.SPRING_PROFILE_TEST)
public class DatabaseInitializerIntTest {

    @Autowired
    private ContactRepository contactRepository;

    private DatabaseInitializer databaseInitializer;

    @Before
    public void setup() {
        databaseInitializer = new DatabaseInitializer(contactRepository);
    }

    @Test
    public void testDatabaseInitializing() throws Exception {
        int initialSize = contactRepository.findAll().size();
        databaseInitializer.run();
        int sizeAfterInitialization = contactRepository.findAll().size();
        Assertions.assertThat(initialSize).isLessThan(sizeAfterInitialization);
    }
}
