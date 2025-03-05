package com.example.notesapp.Notes

import com.example.notesapp.Notes.domain.model.Note
import com.example.notesapp.Notes.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder):NotesEvent()
    data class DeleteNote(val note: Note):NotesEvent()
    object RestoreNote:NotesEvent()
    object ToggleOrderSection:NotesEvent()

}