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
            log.info("Preloading " + repository.save(new Customer(1L,"Sam", "Soori", "India", 35, "sam.soori@gmail.com", 1100)));
            log.info("Preloading " + repository.save(new Customer(2L,"Maya", "Moni", "India", 20, "maya.moni@gmail.com", 600)));
            log.info("Preloading " + repository.save(new Customer(3L,"Lora", "Lee", "USA", 40, "Lora.Lee@gmail.com", 300)));

            log.info("Preloading " + repository.save(new Customer(4L,"Ram", "Soori", "India", 15, "Ram.soori@gmail.com", 1600)));
            log.info("Preloading " + repository.save(new Customer(5L,"Kaya", "Moni", "India", 10, "kaya.moni@gmail.com", 700)));
            log.info("Preloading " + repository.save(new Customer(6L,"Dora", "Lee", "USA", 70, "dora.Lee@gmail.com", 400)));

            log.info("Preloading " + repository.save(new Customer(7L,"Tam", "Soori", "India", 31, "Tam.soori@gmail.com", 2000)));
            log.info("Preloading " + repository.save(new Customer(8L,"Laya", "Moni", "India", 21, "Laya.moni@gmail.com", 900)));
            log.info("Preloading " + repository.save(new Customer(9L,"Rora", "Lee", "USA", 41, "Rora.Lee@gmail.com", 200)));
        };
    }

}
