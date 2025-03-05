package com.example.moviesapp.movielist.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.movielist.data.remote.respnod.movieDto

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoveDatabase:RoomDatabase() {
    abstract val movieDao:MovieDao
}