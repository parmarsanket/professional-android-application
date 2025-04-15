package com.sanket.exam.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apiservice {
    @GET("filter.php?c=Seafood")
    suspend fun getmeals(): mydto


    @GET("lookup.php")
    suspend fun getbyid(@Query("i") id: Int):MealsResponse


}