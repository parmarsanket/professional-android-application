package com.example.androidblogs.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [BlogEntity::class,BlogContentEntity::class],
    version = 2, exportSchema = true

)
abstract  class BlogDatabase :RoomDatabase(){

        abstract fun blogDao ():BlogDao

}