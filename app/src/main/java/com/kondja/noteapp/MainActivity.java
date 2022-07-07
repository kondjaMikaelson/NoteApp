package com.kondja.noteapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kondja.noteapp.Database.NoteViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.NoteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notes -> {
            adapter.submitList(notes);
        });

    }
}