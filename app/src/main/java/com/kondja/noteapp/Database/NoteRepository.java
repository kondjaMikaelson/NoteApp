package com.kondja.noteapp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kondja.noteapp.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    NoteRepository(Application application){
        NoteDatabase db = NoteDatabase.getInstance(application);
        noteDao = db.NoteDao();
        //"Todo: test to see if this works"
        allNotes = noteDao.getAll();
        allNotes = noteDao.getAllAsc();

    }
}
