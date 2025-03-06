package com.example.moviesapp.movielist.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object HomesScreen :Route
    @Serializable
    data class MovieScreen(val id:Int) :Route
}

