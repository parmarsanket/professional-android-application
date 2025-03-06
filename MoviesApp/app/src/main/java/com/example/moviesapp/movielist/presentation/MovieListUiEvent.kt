package com.example.moviesapp.movielist.presentation

sealed class MovieListUiEvent {
    data class paginate(val category:String):MovieListUiEvent()
    object Navigate:MovieListUiEvent()

}