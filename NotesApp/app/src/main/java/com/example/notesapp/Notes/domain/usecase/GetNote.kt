package com.example.notesapp.Notes.domain.usecase

import com.example.notesapp.Notes.domain.Repo.NoteRepo
import com.example.notesapp.Notes.domain.model.Note

class GetNote(
    private val repo: NoteRepo
) {
        suspend operator fun invoke(id: Int):Note?
        {
            return repo.getNoteById(id )
        }
}