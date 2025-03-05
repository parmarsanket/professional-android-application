package com.example.notesapp.Notes.presentation.AddEditViewModel

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnterTitle(val value: String):AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditNoteEvent()
    data class EnterContent(val value: String):AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState):AddEditNoteEvent()
    data class ChangeColor(val color:Int):AddEditNoteEvent()
    object SaveNote:AddEditNoteEvent()



}