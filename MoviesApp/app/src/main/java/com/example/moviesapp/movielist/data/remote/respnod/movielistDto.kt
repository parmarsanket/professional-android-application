package com.example.moviesapp.movielist.data.remote.respnod

import kotlinx.serialization.Serializable

@Serializable
data class movielistDto(
    val page: Int,
    val results: List<movieDto>
)