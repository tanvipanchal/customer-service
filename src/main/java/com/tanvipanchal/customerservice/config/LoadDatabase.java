package com.tanvipanchal.customerservice.config;

import com.tanvipanchal.customerservice.model.Customer;
import com.tanvipanchal.customerservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Customer(1L,"Sam", "Soori", "India", 35, "sam.soori@gmail.com")));
            log.info("Preloading " + repository.save(new Customer(2L,"Maya", "Moni", "India", 20, "maya.moni@gmail.com")));
        };
    }

}
