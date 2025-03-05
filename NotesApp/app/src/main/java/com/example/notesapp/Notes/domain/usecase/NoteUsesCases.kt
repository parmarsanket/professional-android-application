package com.example.notesapp.Notes.domain.usecase

import javax.inject.Inject

data class NoteUsesCases @Inject constructor(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
