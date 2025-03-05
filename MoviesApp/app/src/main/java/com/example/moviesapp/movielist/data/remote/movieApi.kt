package com.example.moviesapp.movielist.data.remote

import com.example.moviesapp.movielist.data.remote.respnod.movielistDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface movieApi   {
    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("page") page:Int,
        @Query("api_key") api_key:String = apikey
    ):movielistDto

    companion object
    {
        const val Base_URL  = "https://api.themoviedb.org/3/"
        const val Image_Base_URL  = "https://image.tmd.org/t/p/w500"
        const val apikey = "b7b51a31617bb17b5e9fabbde5c13320"
    }
}