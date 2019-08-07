package com.github.kdlug.api.v1;

import com.github.kdlug.api.v1.resource.CustomerResource;
import com.github.kdlug.api.v1.resource.CustomerResourceAssembler;
import com.github.kdlug.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    CustomerResourceAssembler assembler;

    public CustomerController(CustomerResourceAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/{customerId}")
    public CustomerResource getCustomerById(@PathVariable long customerId) {
        Customer customer = getCustomer(customerId);

        return assembler.toResource(customer);

    }

    private Customer getCustomer(long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Joe");
        customer.setLastname("Bonamassa");
        customer.setEmail("joe.bonamassa@gmail.com");
        customer.setPassword("xxxxxx");

        return customer;
    }
}
