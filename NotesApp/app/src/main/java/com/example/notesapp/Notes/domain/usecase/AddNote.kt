package com.example.notesapp.Notes.domain.usecase

import com.example.notesapp.Notes.domain.Repo.NoteRepo
import com.example.notesapp.Notes.domain.model.InvalidNoteExcetion
import com.example.notesapp.Notes.domain.model.Note
import javax.inject.Inject
import kotlin.jvm.Throws

class AddNote @Inject constructor(
    private val repo:NoteRepo
) {
    @Throws(InvalidNoteExcetion::class)
    suspend operator fun invoke(note: Note)
    {
        if(note.title.isBlank())
        {
            throw InvalidNoteExcetion("the title of the note can't be empty")

        }
        if(note.content.isBlank())
        {
            throw InvalidNoteExcetion("the content of the note can't be empty")

        }
        repo.insertNote(note)
    }

}