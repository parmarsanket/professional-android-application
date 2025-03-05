package com.example.moviesapp.movielist.di

import com.example.moviesapp.movielist.data.remote.movieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


object Retrofectory
{
    @OptIn(ExperimentalSerializationApi::class)
    fun createRetrofitInstance(): Retrofit
    {
        val json = Json {
            ignoreUnknownKeys = true // Ignores unknown fields in JSON response
            prettyPrint = true       // Formats JSON for readability (optional)
            isLenient = true         // Allows relaxed JSON parsing
        }
        return Retrofit.Builder()
            .baseUrl(movieApi.Base_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}