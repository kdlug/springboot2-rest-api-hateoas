package com.github.kdlug.api.v1.resource;

import com.github.kdlug.entity.Note;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.Date;

@Relation(value = "note", collectionRelation = "notes")
public class NoteResource extends ResourceSupport {
    private Long customerId;
    private Date createdAt;
    private String note;
//
//    public NoteResource(Note note) {
//        this.customerId = note.getCustomerId();
//        this.createdAt = note.getCreatedAt();
//        this.note = note.getNote();
//    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
