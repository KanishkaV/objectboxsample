package com.example.objectboxsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.objectboxsample.ObjecboxSampleApp;
import com.example.objectboxsample.R;
import com.example.objectboxsample.adapter.NotesRecycleViewAdapter;
import com.example.objectboxsample.database.NoteDBAccess;
import com.example.objectboxsample.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {

    private NoteDBAccess noteDBAccess;
    private RecyclerView recyclerView;
    private TextView noNotes;
    public static final String EDIT_NOTE_STRING = "editeNote";
    public static final String NOTE_ID_STRING = "noteId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
        loadNotes();

        FloatingActionButton addNoteBtn = findViewById(R.id.addNotebtn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

    }

    private void initDB() {
        BoxStore boxStore = ((ObjecboxSampleApp) getApplication()).getBoxStore();
        noteDBAccess = new NoteDBAccess(boxStore);
    }

    public void addNote() {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        intent.putExtra(EDIT_NOTE_STRING, false);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        loadNotes();
        super.onResume();
    }

    private void loadNotes() {
        recyclerView = findViewById(R.id.notesRecyclerView);
        noNotes = findViewById(R.id.noNotesText);
        List<Note> notes = noteDBAccess.getNotes();
        if (notes.size() > 0) {
            noNotes.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            NotesRecycleViewAdapter notesAdapter = new NotesRecycleViewAdapter(notes, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(notesAdapter);
        } else {
            noNotes.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}