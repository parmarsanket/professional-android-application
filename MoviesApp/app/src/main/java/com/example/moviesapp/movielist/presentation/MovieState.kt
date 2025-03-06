package com.example.moviesapp.movielist.presentation

import com.example.moviesapp.movielist.domain.model.Movie

data class MovieState (
    val isLoading: Boolean = false,
    val popularMovieListPages :Int =1,
    val upcomingMovieListPages :Int =1,

    val isCurrentPopularScreen :Boolean =true,

    val popularMovieList :List<Movie> = emptyList() ,
    val upcomingMovieList :List<Movie> = emptyList() ,



)