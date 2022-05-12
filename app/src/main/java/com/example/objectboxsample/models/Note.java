package com.example.objectboxsample.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Note {
    @Id
    private long id;
    private String title;
    private String noteBody;

    public Note() {
    }

    public Note(long id, String title, String noteBody) {
        this.id = id;
        this.title = title;
        this.noteBody = noteBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
}
