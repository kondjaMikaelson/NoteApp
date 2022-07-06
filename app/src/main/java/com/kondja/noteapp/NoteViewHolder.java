package com.kondja.noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView noteView;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        noteView = itemView.findViewById(R.id.textView);

    }
    public void bind(String text){
        noteView.setText(text);
    }
    static NoteViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        return new NoteViewHolder(view);
    }
}
