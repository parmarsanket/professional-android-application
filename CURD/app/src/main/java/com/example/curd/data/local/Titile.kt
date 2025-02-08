package com.example.curd.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTitle")
    data class userTitile(
    @PrimaryKey(autoGenerate = true)
        val id: Int?=null,
        val title:String
    )

