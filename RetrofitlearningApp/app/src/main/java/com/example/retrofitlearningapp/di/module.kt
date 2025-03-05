package com.example.retrofitlearningapp.di

import com.example.retrofitlearningapp.data.remote.dto.ApiService
import com.example.retrofitlearningapp.data.remote.dto.Retrofectory
import com.example.retrofitlearningapp.data.repoimp.Repoimp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object module
{
    @Provides
    @Singleton
    fun prodiveRetrofit():Retrofit
    {
        return Retrofectory.createRetrofitInstance()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit):ApiService
    {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideRepo(apiService: ApiService):Repoimp
    {
        return Repoimp(apiService)
    }


}