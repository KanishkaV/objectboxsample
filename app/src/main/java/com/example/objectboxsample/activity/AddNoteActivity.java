package com.example.objectboxsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.objectboxsample.ObjecboxSampleApp;
import com.example.objectboxsample.R;
import com.example.objectboxsample.database.NoteDBAccess;
import com.example.objectboxsample.models.Note;

import io.objectbox.BoxStore;

public class AddNoteActivity extends AppCompatActivity {

    private TextView addNoteViewText;
    private EditText titletXt;
    private EditText noteTxt;
    private Button saveNoteBtn;
    private NoteDBAccess noteDBAccess;
    private Button deleteNoteBtn;
    private Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        BoxStore boxStore = ((ObjecboxSampleApp) getApplication()).getBoxStore();
        noteDBAccess = new NoteDBAccess(boxStore);

        Intent intent = getIntent();

        titletXt = findViewById(R.id.editNoteTitileText);
        noteTxt = findViewById(R.id.editNoteText);
        saveNoteBtn = findViewById(R.id.saveNoteBtn);

        if (intent.getBooleanExtra(MainActivity.EDIT_NOTE_STRING, false)) {
            selectedNote = noteDBAccess.getNote(intent.getLongExtra(MainActivity.NOTE_ID_STRING, 0));
            titletXt.setText(selectedNote.getTitle());
            noteTxt.setText(selectedNote.getNoteBody());
            addNoteViewText = findViewById(R.id.addNoteViewText);
            addNoteViewText.setText("Edit Note.");
            deleteNoteBtn = findViewById(R.id.deleteNoteBtn);
            deleteNoteBtn.setVisibility(View.VISIBLE);
            deleteNoteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteDeleteClicked(selectedNote);
                }
            });
        }
        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoteSaveClicked();
            }
        });
    }

    private void onNoteSaveClicked() {
        Note note = new Note();
        if (selectedNote != null) {
            note.setId(selectedNote.getId());
        }

        note.setTitle(titletXt.getText().toString());
        note.setNoteBody(noteTxt.getText().toString());
        noteDBAccess.addNote(note);
        finish();
    }

    private void onNoteDeleteClicked(Note note) {
        noteDBAccess.removeNote(note.getId());
        finish();
    }
}