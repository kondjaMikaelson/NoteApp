package com.kondja.noteapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kondja.noteapp.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version=1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao NoteDao();
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
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                NoteDao dao = instance.NoteDao();
                dao.deleteAll();

                Note note = new Note("Hello", "This a short body");
                dao.insert(note);
                note = new Note("World", "This is a note with a long body");
                dao.insert(note);
            });
        }
    };

}
