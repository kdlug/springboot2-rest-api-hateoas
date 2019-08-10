package com.github.kdlug.api.v1;

import com.github.kdlug.api.v1.resource.CustomerResource;
import com.github.kdlug.api.v1.resource.CustomerResourceAssembler;
import com.github.kdlug.api.v1.resource.NoteResource;
import com.github.kdlug.api.v1.resource.NoteResourceAssembler;
import com.github.kdlug.entity.Customer;
import com.github.kdlug.entity.Note;
import com.github.kdlug.service.CustomerService;
import com.github.kdlug.service.NoteService;
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
@RequestMapping("/api/v1/notes")
public class NoteController {
    NoteService service;
    NoteResourceAssembler assembler;

    public NoteController(NoteService service, NoteResourceAssembler assembler) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping
    public Resources getNotes() {
        List<Note> notes = service.getNotes();

        Link self = linkTo(methodOn(this.getClass())
                .getNotes()).withSelfRel();

        return new Resources<>(assembler.toResources(notes), self);
    }

    @GetMapping("/{noteId}")
    public NoteResource getNoteById(@PathVariable long noteId) {
        Note note = service.getNote(noteId);

        Link notes = linkTo(methodOn(this.getClass()).getNotes()).withRel("notes");

        NoteResource resource = assembler.toResource(note);
        resource.add(notes);

        return resource;
    }
}
