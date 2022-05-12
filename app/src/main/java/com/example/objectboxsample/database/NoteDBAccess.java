package com.example.objectboxsample.database;

import com.example.objectboxsample.models.Note;
import com.example.objectboxsample.models.Note_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

public class NoteDBAccess {
    private Box<Note> noteBox;

    public NoteDBAccess(BoxStore boxStore) {
        this.noteBox = boxStore.boxFor(Note.class);
    }

    public void addNote(Note note) {
        if (note != null) {
            noteBox.put(note);
        }
    }

    public List<Note> getNotes() {
        return noteBox.getAll();
    }

    public Note getNote(long id) {
        Query<Note> query = noteBox.query(Note_.id.equal(id)).build();
        Note result = query.findFirst();
        query.close();
        return result;
    }

    public void removeNote(long id) {
        Note note = noteBox.get(id);
        noteBox.remove(note);
    }
}
