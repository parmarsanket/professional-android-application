package com.example.notesapp.Notes.presentation.AddEditViewModel

import androidx.compose.runtime.State

data class NoteTextFieldState (
    val text:String ="",
    val hint:String = "",
    val isHindiVisible:Boolean = true
)