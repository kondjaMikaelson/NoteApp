package com.kondja.noteapp.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kondja.noteapp.Note;

import java.util.List;


public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private final LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //TODO: add methods for all other tasks to be performed on the data(Notes)
    public void insert(Note note){
        noteRepository.insert(note);
    }
}
