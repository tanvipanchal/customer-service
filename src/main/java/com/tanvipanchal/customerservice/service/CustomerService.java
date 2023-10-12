package com.tanvipanchal.customerservice.service;

import com.tanvipanchal.customerservice.exception.CustomerBadRequestException;
import com.tanvipanchal.customerservice.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerService {

    private static final String subscriptionTierGold = "GOLD";
    private static final String subscriptionTierSilver = "SILVER";
    private static final String subscriptionTierBronze = "BRONZE";

    public List<Customer> getCustomerBySubscriptionTier(String subscriptionTier, List<Customer> customers) {

        HashMap<String, List<Customer>> customerMap = getCustomersMap(customers);
        if(subscriptionTier == null || subscriptionTier.isEmpty()) {
            return customers;
        }
        else if(subscriptionTier.equalsIgnoreCase(subscriptionTierGold)){
            return customerMap.get(subscriptionTierGold);
        }else if(subscriptionTier.equalsIgnoreCase(subscriptionTierSilver)){
            return customerMap.get(subscriptionTierSilver);
        }else if(subscriptionTier.equalsIgnoreCase(subscriptionTierBronze)) {
            return customerMap.get(subscriptionTierBronze);
        }
        return null;
    }

    public HashMap<String, List<Customer>> getCustomersMap(List<Customer> customers){
        HashMap<String, List<Customer>> customerMap = new HashMap<>();

        List<Customer> goldCustomers = new ArrayList<>();
        List<Customer> silverCustomers = new ArrayList<>();
        List<Customer> bronzeCustomers = new ArrayList<>();

        for(Customer cust : customers){
            double customerPurchaseAmount = cust.getPurchaseAmount();
            if(customerPurchaseAmount < 500){
                bronzeCustomers.add(cust);
            } else if(customerPurchaseAmount >= 500 && customerPurchaseAmount <= 1000){
                silverCustomers.add(cust);
            }else if(customerPurchaseAmount > 1000){
                goldCustomers.add(cust);
            }
        }
        customerMap.put(subscriptionTierGold, goldCustomers);
        customerMap.put(subscriptionTierSilver, silverCustomers);
        customerMap.put(subscriptionTierBronze, bronzeCustomers);

        return customerMap;
    }

    public void validateCustomerRequest(Customer customer){
        if(customer == null){
            throw new CustomerBadRequestException(null, "Customer request is null, please provide customer request.");
        } else if(customer.getId() == null){
            throw new CustomerBadRequestException(null, "Customer id is required, please provide customer id in request.");
        }else if(customer.getFirstName() == null || customer.getFirstName().isEmpty() ||
                customer.getLastName() == null || customer.getLastName().isEmpty() ||
                customer.getCountry() == null ||customer.getCountry().isEmpty() ||
                customer.getEmail() ==null || customer.getEmail().isEmpty() ||
                (customer.getAge() == 0) ||
                customer.getPurchaseAmount() == 0){
            throw new CustomerBadRequestException(customer.getId(), "Customer first name, last name, age, email, country " +
                    "and purchaseAmount are required, some fields are missing, please provide input.");
        }
    }
}
