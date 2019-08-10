package com.github.kdlug.api.v1.resource;

import com.github.kdlug.api.v1.CustomerController;
import com.github.kdlug.entity.Customer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CustomerResourceAssembler extends ResourceAssemblerSupport<Customer, CustomerResource> {
    private final Class<?> controllerClass;

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     */
    public CustomerResourceAssembler() {
        super(CustomerController.class, CustomerResource.class);
        this.controllerClass = CustomerController.class;
    }

    @Override
    public CustomerResource toResource(Customer entity) {
        // Creates a new resource with a self link to the given id
        CustomerResource resource = createResourceWithId(entity.getId(), entity);

        resource.setName(entity.getName());
        resource.setLastname(entity.getLastname());
        resource.setEmail(entity.getEmail());

        resource.add(getNotesLink(entity.getId()));

        return resource;
    }

    private Link getNotesLink(long customerId) {
        return linkTo(methodOn(CustomerController.class).getCustomerByIdNotes(customerId)).withRel("customer_notes");
    }
}
