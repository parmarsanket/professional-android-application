package com.example.moviesapp.movielist.data.local.movie

import androidx.room.Dao
import androidx.room.Upsert
import androidx.room.Query

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovielist(movieList:List<MovieEntity>)

    @Query("select * from movieEntity where id=:id")
    suspend fun getMoviebyId(id:Int):MovieEntity

    @Query("select * from movieEntity where category=:category")
    suspend fun getMoviebyId(category:String):List<MovieEntity>




}