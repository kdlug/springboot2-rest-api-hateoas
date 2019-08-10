package com.github.kdlug.api.v1.resource;

import com.github.kdlug.api.v1.NoteController;
import com.github.kdlug.entity.Note;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class NoteResourceAssembler extends ResourceAssemblerSupport<Note, NoteResource> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     */
    public NoteResourceAssembler() {
        super(NoteController.class, NoteResource.class);
    }

    @Override
    public NoteResource toResource(Note entity) {
        // Creates a new resource with a self link to the given id
        NoteResource resource = createResourceWithId(entity.getId(), entity);

        resource.setCustomerId(entity.getCustomerId());
        resource.setCreatedAt(entity.getCreatedAt());
        resource.setNote(entity.getNote());

        return resource;
    }
}
