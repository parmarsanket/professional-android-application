package com.example.curd.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [userTitile::class], version = 1, exportSchema = false)
abstract class titleDatabased :RoomDatabase(){
    abstract fun user():TitleDao
}