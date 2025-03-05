package com.example.notesapp.Notes.domain.Repo

import com.example.notesapp.Notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {
    fun getNotes():Flow<List<Note>>

    suspend fun getNoteById(int: Int):Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}