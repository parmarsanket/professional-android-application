package com.sanket.exam.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class mydto(
    @SerialName("meals")
    val meals: List<Meal>
)