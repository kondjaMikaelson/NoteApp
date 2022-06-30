package com.kondja.noteapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kondja.noteapp.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version=1)
public abstract class NoteDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(final Context context){
        if(instance==null) {
            synchronized (NoteDatabase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }

}
