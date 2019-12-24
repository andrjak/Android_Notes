package com.artisanter.noteapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

class NoteAdapter extends ArrayAdapter<Note> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Note> notes;
    private SimpleDateFormat dateFormat;
    @SuppressLint("SimpleDateFormat")
    NoteAdapter(Context context, int resource, ArrayList<Note> notes) {
        super(context, resource, notes);
        this.notes = notes;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
    }
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);
        TextView title = view.findViewById(R.id.title);
        TextView content = view.findViewById(R.id.content);
        TextView tags = view.findViewById(R.id.tags);
        TextView date = view.findViewById(R.id.date);


        Note note = notes.get(position);

        title.setText(note.title);
        date.setText(dateFormat.format(note.date));
        content.setText(note.content);
        if(note.tags.isEmpty()){
            tags.setVisibility(View.GONE);
            content.setMaxLines(content.getMaxLines() + 1);
            content.setMinLines(content.getMinLines() + 1);
        }
        else {
            tags.setVisibility(View.VISIBLE);
        }
        tags.setText(note.tags);
        return view;
    }
}
