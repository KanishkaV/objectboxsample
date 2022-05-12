package com.example.objectboxsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.objectboxsample.R;
import com.example.objectboxsample.activity.AddNoteActivity;
import com.example.objectboxsample.activity.MainActivity;
import com.example.objectboxsample.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesRecycleViewAdapter extends RecyclerView.Adapter<NotesRecycleViewAdapter.ViewHolder> {

    private List<Note> notes;
    private Context context;
    private ViewGroup parent;

    public NotesRecycleViewAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent=parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.getTitleTextView().setText(notes.get(position).getTitle());
        holder.getNoteIdTextView().setText(notes.get(position).getId() + "");
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddNoteActivity.class);
                intent.putExtra(MainActivity.NOTE_ID_STRING,notes.get(position).getId());
                intent.putExtra(MainActivity.EDIT_NOTE_STRING,true);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView noteIdTxt;
        public RelativeLayout parent;
        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            titleText = itemView.findViewById(R.id.txtDisplayName);
            noteIdTxt = itemView.findViewById(R.id.noteIdTxtView);
            parent=itemView.findViewById(R.id.parentLayout);

        }

        public TextView getTitleTextView() {
            return titleText;
        }

        public TextView getNoteIdTextView() {
            return noteIdTxt;
        }
    }
}
