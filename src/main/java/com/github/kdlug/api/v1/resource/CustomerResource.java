package com.github.kdlug.api.v1.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.kdlug.entity.Note;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.hateoas.core.Relation;

import java.util.ArrayList;
import java.util.List;

@Relation(value = "customer", collectionRelation = "customers")
public class CustomerResource extends ResourceSupport {
    private String name;
    private String lastname;
    private String email;
    @JsonUnwrapped
    private Resources<NoteResource> notes;

    public void embedNotes(Resources<NoteResource> notes) {
        this.notes = notes;
    }

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
