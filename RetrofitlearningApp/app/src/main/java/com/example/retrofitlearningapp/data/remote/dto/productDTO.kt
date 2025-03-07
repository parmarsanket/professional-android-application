package com.example.retrofitlearningapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
@Serializable
data class Rating(
    val count: Int,
    val rate: Double
)