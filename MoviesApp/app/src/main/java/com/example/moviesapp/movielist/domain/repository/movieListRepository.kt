package com.example.moviesapp.movielist.domain.repository

import com.example.moviesapp.movielist.util.Resource
import kotlinx.coroutines.flow.Flow

interface movieListRepository {
    suspend fun getMovieList(
        forcedfetchFormRemote:Boolean,
        category:String,
        page:Int
    ):Flow<Resource<List<com.example.moviesapp.movielist.domain.model.Movie>>>

    suspend fun getMovie(id:Int):Flow<Resource<com.example.moviesapp.movielist.domain.model.Movie>>




}