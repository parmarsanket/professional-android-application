package com.example.notesapp.Notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.notesapp.Notes.domain.model.Note
import com.example.notesapp.Notes.domain.usecase.NoteUsesCases
import com.example.notesapp.Notes.util.NoteOrder
import com.example.notesapp.Notes.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUsesCases: NoteUsesCases
):ViewModel(){


    private var getNotesjob  : Job? = null
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recetlyDel :Note?= null
init {
    getNotes(NoteOrder.Data(OrderType.Descending))
}

    fun onEvent(event: NotesEvent)
    {
        when(event)
        {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUsesCases.deleteNote(event.note)
                    recetlyDel = event.note
                }
            }
            is NotesEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType== event.noteOrder.orderType)
                {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch  {
                 noteUsesCases.addNote(recetlyDel?:return@launch)
                    recetlyDel = null

                }
            }
            is NotesEvent.ToggleOrderSection ->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {

        getNotesjob?.cancel()
        getNotesjob= noteUsesCases.getNotes(noteOrder ).onEach {
             notes->
             _state.value = _state.value.copy(
                 notes = notes,noteOrder=noteOrder )
         }.launchIn(viewModelScope)

    }


}