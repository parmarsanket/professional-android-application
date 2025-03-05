package com.example.notesapp.Notes.presentation.AddEditViewModel

import android.media.metrics.Event
import android.os.Message
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Notes.domain.model.InvalidNoteExcetion
import com.example.notesapp.Notes.domain.model.Note
import com.example.notesapp.Notes.domain.usecase.NoteUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUsesCases: NoteUsesCases,
):ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter the title.."
    ))
    val noteTitle:State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter same Content"
    ))
    val noteContent:State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf<Int>(Note.notColors.random().toArgb())
    val noteColor  : State<Int> =_noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val currentId :Int? = null
    fun onEvent(event: AddEditNoteEvent)
    {
        when(event)
        {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHindiVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHindiVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnterContent ->{
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.EnterTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUsesCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentId

                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)

                    }catch (e:InvalidNoteExcetion)
                    {

                        _eventFlow.emit(UiEvent.ShowSnackbar(message = e.message?:"Unknow error"))
                    }
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String):UiEvent()
        object SaveNote:UiEvent()
    }
}