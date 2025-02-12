package com.example.curdnew.Data.Local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Title::class], version = 1)
abstract class Databased:RoomDatabase() {
    abstract fun titleDao():TitleDao
}