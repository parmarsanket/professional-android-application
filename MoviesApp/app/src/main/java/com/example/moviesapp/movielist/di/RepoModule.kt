package com.example.moviesapp.movielist.di

import com.example.moviesapp.movielist.data.repository.movieListRepositoryImp
import com.example.moviesapp.movielist.domain.repository.movieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun bindMovieListRepo(
        movieListRepositoryImp: movieListRepositoryImp
    ):movieListRepository



}