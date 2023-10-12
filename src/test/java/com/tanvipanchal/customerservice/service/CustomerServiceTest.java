package com.tanvipanchal.customerservice.service;

import com.tanvipanchal.customerservice.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    List<Customer> customers = new ArrayList<>();

    @BeforeEach
    void setUp() {
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
    void getCustomerBySubscriptionTier_TestNullSubscriptionTier() {
        CustomerService cs = new CustomerService();

        List<Customer> customer = cs.getCustomerBySubscriptionTier(null, customers);
        assertNotNull(customer);
        assertEquals(3, customer.size());
    }

    @Test
    void getCustomerBySubscriptionTier_TestGoldSubscriptionTier() {
        CustomerService cs = new CustomerService();

        List<Customer> customer = cs.getCustomerBySubscriptionTier("GOLD", customers);
        assertNotNull(customer);
        assertEquals(1, customer.size());
        assertEquals("Rina",customer.get(0).getFirstName());
    }

    @Test
    void getCustomerBySubscriptionTier_TestSilverSubscriptionTier() {
        CustomerService cs = new CustomerService();

        List<Customer> customer = cs.getCustomerBySubscriptionTier("Silver", customers);
        assertNotNull(customer);
        assertEquals(1, customer.size());
        assertEquals("Mina",customer.get(0).getFirstName());
    }

    @Test
    void getCustomerBySubscriptionTier_TestBronzeSubscriptionTier() {
        CustomerService cs = new CustomerService();

        List<Customer> customer = cs.getCustomerBySubscriptionTier("bronze", customers);
        assertNotNull(customer);
        assertEquals(1, customer.size());
        assertEquals("Tina",customer.get(0).getFirstName());
    }

}