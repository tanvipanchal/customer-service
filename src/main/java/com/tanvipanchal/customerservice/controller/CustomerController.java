package com.tanvipanchal.customerservice.controller;

import com.tanvipanchal.customerservice.exception.CustomerNotFoundException;
import com.tanvipanchal.customerservice.model.Customer;
import com.tanvipanchal.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/v1/customers")
    public Customer createCustomer(@RequestBody Customer newCustomer){
        return repository.save(newCustomer);
    }

    @GetMapping("/v1/customers")
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
