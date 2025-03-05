package com.example.moviesapp.movielist.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.movielist.data.local.movie.MoveDatabase
import com.example.moviesapp.movielist.data.remote.movieApi
import com.example.moviesapp.movielist.domain.model.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideretrofit():Retrofit
    {
        return Retrofectory.createRetrofitInstance()
    }


    @Singleton
    @Provides
    fun providemovieApi (retrofit: Retrofit):movieApi{
        return retrofit.create(movieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDatabase (app:Application):MoveDatabase
    {
        return Room.databaseBuilder(
            app,
            MoveDatabase::class.java,
            "moviedb"
        ).build()
    }

}