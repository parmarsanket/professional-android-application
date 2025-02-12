package com.example.curdnew.presentation

import com.example.curdnew.Data.Local.Title

data class AppState(
val id:Int?=null,
    val title:String="",
    val titles:List<Title> = emptyList()
)


