package com.github.kdlug.service;

import com.github.kdlug.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoteService {
    public Note getNote(long noteId) {
        Note note = new Note();
        note.setId(noteId);
        note.setCustomerId(1l);
        note.setCreatedAt(new Date(System.currentTimeMillis()));
        note.setNote("Note " + noteId);

        return note;
    }

    public Note getNote(long noteId, long customerId) {
        Note note = new Note();
        note.setId(noteId);
        note.setCustomerId(customerId);
        note.setCreatedAt(new Date(System.currentTimeMillis()));
        note.setNote("Note " + noteId);

        return note;
    }

    public List<Note> getNotes(Long customerId) {
        List<Note> notes = new ArrayList<>();
        notes.add(getNote(1l, customerId));
        notes.add(getNote(2l, customerId));
        notes.add(getNote(3l, customerId));

        return notes;
    }

    public List<Note> getNotes() {
        return getNotes(1l);
    }
}
