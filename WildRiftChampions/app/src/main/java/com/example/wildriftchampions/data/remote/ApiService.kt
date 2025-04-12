package com.example.wildriftchampions.data.remote

import ChampionResponse
import retrofit2.http.GET


interface ApiService {
    @GET("champion.json")
    suspend fun getchampion():ChampionResponse
}