package com.example.notesapp.Notes.domain.usecase

import com.example.notesapp.Notes.domain.Repo.NoteRepo
import com.example.notesapp.Notes.domain.model.Note
import javax.inject.Inject

class DeleteNote @Inject constructor (
    private val repo: NoteRepo
){
    suspend operator fun invoke(note:Note){
        repo.deleteNote(note)
    }
}