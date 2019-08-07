package com.github.kdlug.service;

import com.github.kdlug.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    public Customer getCustomer(long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Joe");
        customer.setLastname("Bonamassa");
        customer.setEmail("joe.bonamassa@gmail.com");
        customer.setPassword("xxxxxx");

        return customer;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(getCustomer(1));
        customers.add(getCustomer(2));
        customers.add(getCustomer(3));
        customers.add(getCustomer(4));
        customers.add(getCustomer(5));
        customers.add(getCustomer(6));
        customers.add(getCustomer(7));
        customers.add(getCustomer(8));
        customers.add(getCustomer(9));
        customers.add(getCustomer(10));

        return customers;
    }
}
