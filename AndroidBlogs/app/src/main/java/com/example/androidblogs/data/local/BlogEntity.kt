package com.example.androidblogs.data.local

import android.provider.MediaStore.Images.Thumbnails
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogEntity(
    @PrimaryKey
    val id :Int,
    val title:String,
    val thumbnailUrl: String,
    val contentUrl:String
)
