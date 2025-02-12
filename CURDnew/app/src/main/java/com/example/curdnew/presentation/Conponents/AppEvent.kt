package com.example.curdnew.presentation.Conponents

import com.example.curdnew.Data.Local.Title

sealed class AppEvent {
    data object saveEdit:AppEvent()
    data object delete:AppEvent()
    data class OnSubjectNameChange(val name: String): AppEvent()
    data class OnClick(val title: Title):AppEvent()
}