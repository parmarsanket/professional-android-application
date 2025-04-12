package com.example.wildriftchampions.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object retrofectry {
    @OptIn(ExperimentalSerializationApi::class)
    fun createRetrofitInstance(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl("https://ddragon.leagueoflegends.com/cdn/15.7.1/data/en_US/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}