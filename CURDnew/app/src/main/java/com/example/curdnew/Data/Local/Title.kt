package com.example.curdnew.Data.Local

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Title(
    val title: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int?
)
