package com.kondja.noteapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kondja.noteapp.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM notes order by note_title ASC")
    LiveData<List<Note>> getAllAsc();

    @Query("SELECT * FROM notes where id IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM notes where note_title like :title")
    Note findByName(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE from notes")
    void deleteAll();
}
