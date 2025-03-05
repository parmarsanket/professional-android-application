package com.example.retrofitlearningapp.data.remote.dto

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object Retrofectory
{
    fun createRetrofitInstance():Retrofit
    {
        val json = Json {
            ignoreUnknownKeys = true // Ignores unknown fields in JSON response
            prettyPrint = true       // Formats JSON for readability (optional)
            isLenient = true         // Allows relaxed JSON parsing
        }
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}