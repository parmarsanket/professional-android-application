package com.example.curd.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Query("select * from userTitle")
    fun  getAllTitlelist(): Flow<List<userTitile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTilte(title: userTitile)

    @Query("delete from userTitle where id = :Id")
    suspend fun DeletebyId(Id: Int)

    @Upsert
    suspend fun Edit(title: userTitile)

}