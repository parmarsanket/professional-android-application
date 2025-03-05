package com.example.notesapp.Notes.data.locatdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.Notes.domain.model.Note


@Database(
    entities = [Note::class], version = 1
)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun noteDao() : NoteDao
}