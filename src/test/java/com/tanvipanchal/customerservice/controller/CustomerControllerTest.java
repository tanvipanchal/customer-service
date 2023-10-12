package com.tanvipanchal.customerservice.controller;

import com.tanvipanchal.customerservice.exception.CustomerNotFoundException;
import com.tanvipanchal.customerservice.model.Customer;
import com.tanvipanchal.customerservice.repository.CustomerRepository;
import com.tanvipanchal.customerservice.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    List<Customer> customers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("Tina");
        c1.setLastName("Roy");
        c1.setAge(25);
        c1.setPurchaseAmount(300);
        c1.setCountry("India");
        c1.setEmail("tina.roy@gmail.com");
        customers.add(c1);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstName("Mina");
        c2.setLastName("Roy");
        c2.setAge(25);
        c2.setPurchaseAmount(700);
        c2.setCountry("India");
        c2.setEmail("mina.roy@gmail.com");
        customers.add(c2);

        Customer c3 = new Customer();
        c3.setId(3L);
        c3.setFirstName("Rina");
        c3.setLastName("Roy");
        c3.setAge(25);
        c3.setPurchaseAmount(2000);
        c3.setCountry("India");
        c3.setEmail("rina.roy@gmail.com");
        customers.add(c3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCustomer() {
        //Arrange
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customers.get(0));
        //Act
        ResponseEntity<Customer> customer = customerController.createCustomer(customers.get(0));
        //Asset
        assertNotNull(customer);
        assertEquals("Tina", customer.getBody().getFirstName());
    }

    @Test
    void getCustomerBySubscriptionTier() {
        //Arrange
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerService.getCustomerBySubscriptionTier(anyString(), anyList())).thenReturn(customers);

        //Act
        List<Customer> customersList = customerController.getCustomerBySubscriptionTier("GOLD");

        //Assert
        assertNotNull(customersList);
        assertEquals(3, customersList.size());
    }

    @Test
    void getCustomerById_positive() {
        //Arrange
        Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(customers.get(0)));

        //Act
        Customer customer = customerController.getCustomerById(1L);

        //Assert
        assertNotNull(customer);
        assertEquals("Tina", customer.getFirstName());
    }

    @Test
    void getCustomerById_negative() {
        //Arrange
        Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        //Act
        try {
            Customer customer = customerController.getCustomerById(1L);
        }catch(CustomerNotFoundException e){
            //Assert
        }


    }

    @Test
    void updateCustomer_positive() {
        //Arrange
        Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(customers.get(0)));
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customers.get(1));

        //Act
        Customer customer = customerController.updateCustomer(customers.get(2),1L);

        //Assert
        assertNotNull(customer);
        assertEquals("Mina", customer.getFirstName());
    }

    @Test
    void updateCustomer_negative() {
        //Arrange
        Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customers.get(1));

        //Act
        Customer customer = customerController.updateCustomer(customers.get(2),100L);

        //Assert
        assertNotNull(customer);
        assertEquals(2, customer.getId());
    }

    @Test
    void deleteCustomerById() {
        //Arrange
        Mockito.doNothing().when(customerRepository).deleteById(any(Long.class));
    }
}