package com.example.notesapp.Notes.data.Repo

import com.example.notesapp.Notes.data.locatdata.NoteDao
import com.example.notesapp.Notes.domain.Repo.NoteRepo
import com.example.notesapp.Notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

class RepoImp(
    private val dao:NoteDao
):NoteRepo {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(int: Int): Note? {
        return dao.getNoteById(int)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.detele(note)
    }
}
