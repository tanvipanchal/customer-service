package com.tanvipanchal.customerservice.repository;

import com.tanvipanchal.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
