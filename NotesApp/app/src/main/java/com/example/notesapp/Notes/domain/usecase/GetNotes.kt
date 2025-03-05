package com.example.notesapp.Notes.domain.usecase

import com.example.notesapp.Notes.domain.Repo.NoteRepo
import com.example.notesapp.Notes.domain.model.Note
import com.example.notesapp.Notes.util.NoteOrder
import com.example.notesapp.Notes.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val repo: NoteRepo
) {
    operator fun invoke(
        noteOrder:NoteOrder = NoteOrder.Data(OrderType.Descending)
    ):Flow<List<Note>>
    {
        return repo.getNotes().map {
            notes->
            when(noteOrder.orderType)
            {
                is OrderType.Ascending ->
                {
                    when(noteOrder){
                        is NoteOrder.Title->notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Data->notes.sortedBy { it.timestamp }
                        is NoteOrder.Color ->notes.sortedBy { it.color}
                    }

                }
                is OrderType.Descending->
                {
                    when(noteOrder){
                        is NoteOrder.Title->notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Data->notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color ->notes.sortedByDescending { it.color}
                    }

                }
            }
        }

    }
}