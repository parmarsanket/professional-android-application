package com.example.moviesapp.movielist.data.repository

import com.example.moviesapp.movielist.data.local.mapper.toMovie
import com.example.moviesapp.movielist.data.local.mapper.toMovieEntity
import com.example.moviesapp.movielist.data.local.movie.MoveDatabase
import com.example.moviesapp.movielist.data.remote.movieApi
import com.example.moviesapp.movielist.data.remote.respnod.movieDto
import com.example.moviesapp.movielist.domain.model.Movie
import com.example.moviesapp.movielist.domain.repository.movieListRepository
import com.example.moviesapp.movielist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject


class movieListRepositoryImp @Inject constructor(
    private val movieapi:movieApi,
    private val movieDatabase:MoveDatabase
) :movieListRepository
{
    override suspend fun getMovieList(
        forcedfetchFormRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val localmovielist = movieDatabase.movieDao.getMoviebyId(category)
             val shouldLoadMovie = localmovielist.isNotEmpty() && !forcedfetchFormRemote
            if(shouldLoadMovie)
            {
                emit(Resource.Success(
                    data = localmovielist.map { movie->movie.toMovie(category) }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val movielistFromApi = try {
                movieapi.getMovieList(category,page)
            }catch (e:Exception)
            {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading"))
                return@flow
            }


                movieDatabase.movieDao.upsertMovielist(movieList = movielistFromApi.results.map {
                    it.toMovieEntity(category = category)
                })
            emit(Resource.Success(
                movielistFromApi.results.map {
                    it.toMovieEntity(category = category)
                }.map { it.toMovie(category) }
            ))
            emit(Resource.Loading(false))

        }
        }


    override suspend fun getMovie(id: Int): Flow<Resource<Movie>>
    {
        return flow {
            emit(Resource.Loading(true))
            val movieEntity = movieDatabase.movieDao.getMoviebyId(id)
            if(movieEntity!=null)
            {
                emit(
                    Resource.Success( movieEntity.toMovie(movieEntity.category))
                )
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error("no Sach item"))
            emit(Resource.Loading(false))



        }


    }
}
