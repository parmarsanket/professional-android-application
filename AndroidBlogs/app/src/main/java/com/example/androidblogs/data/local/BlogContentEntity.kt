package com.example.androidblogs.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidblogs.data.remote.Contant.Blog_Content_Table_Name

@Entity(tableName = Blog_Content_Table_Name)
data class BlogContentEntity(
    @PrimaryKey
    val blogId:Int,
    val content:String
)
