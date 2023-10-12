package com.tanvipanchal.customerservice.controller;

import com.tanvipanchal.customerservice.exception.CustomerNotFoundException;
import com.tanvipanchal.customerservice.model.Customer;
import com.tanvipanchal.customerservice.repository.CustomerRepository;
import com.tanvipanchal.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    private final CustomerService customerService;

    public CustomerController(CustomerRepository repository, CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @PostMapping("/v1/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer){
        customerService.validateCustomerRequest(newCustomer);
        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
    }

    @GetMapping("/v1/customers")
    public List<Customer> getCustomerBySubscriptionTier(@RequestParam(required = false) String subscriptionTier) {

        return customerService.getCustomerBySubscriptionTier(subscriptionTier, (List<Customer>) repository.findAll());

    }

    @GetMapping("/v1/all-customers")
    public List<Customer> getAllCustomers(){
        return (List<Customer>) repository.findAll();
    }

    @GetMapping("/v1/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = repository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else{
            throw new CustomerNotFoundException(id);
        }
    }

    @PutMapping("/v1/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        customerService.validateCustomerRequest(newCustomer);
        Optional<Customer> customer = repository.findById(id);
        if(customer.isPresent()){
            Customer oldCustomer = customer.get();
            oldCustomer.setFirstName(newCustomer.getFirstName());
            oldCustomer.setLastName(newCustomer.getLastName());
            oldCustomer.setCountry(newCustomer.getCountry());
            oldCustomer.setAge(newCustomer.getAge());
            oldCustomer.setEmail(newCustomer.getEmail());
            return repository.save(oldCustomer);
        }else{
            //throw new CustomerNotFoundException(id);
            newCustomer.setId(id);
            return repository.save(newCustomer);

        }

    }

    @DeleteMapping("/v1/customers/{id}")
    public void deleteCustomerById(@PathVariable Long id){
        repository.deleteById(id);
    }


}
