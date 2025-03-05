package com.example.notesapp.Notes

import com.example.notesapp.Notes.domain.model.Note
import com.example.notesapp.Notes.util.NoteOrder
import com.example.notesapp.Notes.util.OrderType

data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder:NoteOrder = NoteOrder.Data(OrderType.Descending),
    val isOrderSectionVisible :Boolean = false
)
