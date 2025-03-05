package com.example.retrofitlearningapp.data.remote.dto

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<product>


}