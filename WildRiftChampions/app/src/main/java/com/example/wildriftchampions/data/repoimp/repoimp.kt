package com.example.wildriftchampions.data.repoimp

import ChampionResponse
import com.example.wildriftchampions.data.remote.retrofectry
import com.example.wildriftchampions.data.remote.ApiService

class Repoimp {

        private val retrofit =retrofectry.createRetrofitInstance()


        private val apiService = retrofit.create(ApiService::class.java)


    suspend fun getchamp():ChampionResponse
    {
        return apiService.getchampion()
    }
}