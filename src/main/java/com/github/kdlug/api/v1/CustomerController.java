package com.github.kdlug.api.v1;

import com.github.kdlug.api.v1.resource.CustomerResource;
import com.github.kdlug.api.v1.resource.CustomerResourceAssembler;
import com.github.kdlug.entity.Customer;
import com.github.kdlug.service.CustomerService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    CustomerService service;
    CustomerResourceAssembler assembler;

    public CustomerController(CustomerService service, CustomerResourceAssembler assembler) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public Resources getCustomers() {
        List<Customer> customers = service.getCustomers();

        Link selfRel = linkTo(methodOn(CustomerController.class)
                .getCustomers()).withSelfRel();

        return new Resources<>(assembler.toResources(customers), selfRel);
    }

    @GetMapping("/{customerId}")
    public CustomerResource getCustomerById(@PathVariable long customerId) {
        Customer customer = service.getCustomer(customerId);

        return assembler.toResource(customer);
    }
}
