package com.kondja.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kondja.noteapp.Database.NoteDao;
import com.kondja.noteapp.Database.NoteDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteDao noteDao;
    private List<Note> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoteDatabase db = NoteDatabase.getInstance(getApplicationContext());
        noteDao = db.noteDao();
        allNotes = noteDao.getAll();
    }
}