package com.example.notesapp.Notes.data.locatdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.Notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {


    @Query("select * from note")
    fun getNotes():Flow<List<Note>>

    @Query("select * from note where id = :id")
    suspend fun getNoteById(id:Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

     @Delete
     suspend fun detele(note: Note)



}