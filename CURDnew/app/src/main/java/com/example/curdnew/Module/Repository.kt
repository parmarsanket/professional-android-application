package com.example.curdnew.Module

import com.example.curdnew.Data.Local.Title
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getDelete(title: Title)

    suspend fun AddEdit(title: Title)

    fun getAllTitles(): Flow<List<Title>>


}