package com.martingy.phonebookapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.martingy.phonebookapp.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
