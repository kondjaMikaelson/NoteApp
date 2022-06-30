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
        allNotes = noteDao.getAll();
        allNotes = noteDao.getAllAsc();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });
    }
}
