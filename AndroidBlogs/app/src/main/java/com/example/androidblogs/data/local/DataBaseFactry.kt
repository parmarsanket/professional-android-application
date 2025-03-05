package com.example.androidblogs.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidblogs.data.remote.Contant.Blog_DataBase_Name

object DataBaseFactry {
      fun create(context: Context):BlogDatabase
      {
          return Room.databaseBuilder(
              context = context.applicationContext,
              klass = BlogDatabase::class.java,
              name = Blog_DataBase_Name
              ).fallbackToDestructiveMigrationFrom(1)
              .build()
      }
}