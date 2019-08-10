package com.github.kdlug.api.v1;

import com.github.kdlug.api.v1.resource.CustomerResource;
import com.github.kdlug.api.v1.resource.CustomerResourceAssembler;
import com.github.kdlug.api.v1.resource.NoteResourceAssembler;
import com.github.kdlug.entity.Customer;
import com.github.kdlug.entity.Note;
import com.github.kdlug.service.CustomerService;
import com.github.kdlug.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    CustomerService customerService;
    NoteService noteService;
    @Autowired
    CustomerResourceAssembler customerResourceAssembler;
    @Autowired
    NoteResourceAssembler noteResourceAssembler;

    public CustomerController(CustomerService customerService, NoteService noteService) {
        this.customerService = customerService;
        this.noteService = noteService;
    }

    @GetMapping
    public Resources getCustomers() {
        List<Customer> customers = customerService.getCustomers();

        Link self = linkTo(methodOn(this.getClass())
                .getCustomers()).withSelfRel();

        return new Resources<>(customerResourceAssembler.toResources(customers), self);
    }

    @GetMapping("/{customerId}")
    public CustomerResource getCustomerById(@PathVariable long customerId) {
        Customer customer = customerService.getCustomer(customerId);

        Link customers = linkTo(methodOn(this.getClass()).getCustomers()).withRel("customers");

        CustomerResource resource = customerResourceAssembler.toResource(customer);
        resource.add(customers);

        return resource;
    }

    @GetMapping("/{customerId}/notes")
    public Resources getCustomerByIdNotes(@PathVariable long customerId) {
        List<Note> notes = noteService.getNotes(customerId);

        Link self = linkTo(methodOn(this.getClass()).getCustomerByIdNotes(customerId)).withSelfRel();
        Link customers = linkTo(methodOn(this.getClass()).getCustomers()).withRel("customers");

        return new Resources<>(noteResourceAssembler.toResources(notes), self, customers);
    }
}
