package com.example.curdnew.Data.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface TitleDao {

@Upsert
suspend fun upsertTitle(title: Title)

@Delete
suspend fun deleteTitle(title: Title)

@Query( "select * from Title")
fun getAllTitle():Flow<List<Title>>
}