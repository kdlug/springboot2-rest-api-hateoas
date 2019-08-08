package com.github.kdlug.api.v1.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value = "customer", collectionRelation = "customers")
public class CustomerResource extends ResourceSupport {
    private String name;
    private String lastname;
    private String email;

    public String getFullName() {
        return name + " " + lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
