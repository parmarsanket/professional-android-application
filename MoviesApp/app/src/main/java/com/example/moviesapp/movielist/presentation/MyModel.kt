package com.example.moviesapp.movielist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.movielist.domain.repository.movieListRepository
import com.example.moviesapp.movielist.util.Category
import com.example.moviesapp.movielist.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyModel @Inject constructor (
    private val movieListRepo : movieListRepository
):ViewModel() {
    private val _movieListState = MutableStateFlow(MovieState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularmovieList(false)
        getUpcomingMovieList(false)
    }


    fun onEvent(event: MovieListUiEvent)
    {
        when(event)
        {
            MovieListUiEvent.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
                    )
                }
            }
            is MovieListUiEvent.paginate -> {
                if (event.category==Category.POPULAR){
                    getPopularmovieList(true)
                }else if (event.category==Category.UPCOMING)
                {
                    getUpcomingMovieList(true)
                }

            }
        }
    }
    private fun getUpcomingMovieList(forceFechFromRemote:Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true )
            }
            movieListRepo.getMovieList(forcedfetchFormRemote = forceFechFromRemote
                ,Category.POPULAR,
                _movieListState.value.popularMovieListPages).collectLatest {
                    result->
                    when(result)
                    {
                        is Resource.Error -> {
                            _movieListState.update {
                                it.copy(isLoading = false)
                            }
                        }
                        is Resource.Loading -> {
                            _movieListState.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }
                        is Resource.Success -> {
                            result.data?.let {
                                populerlist->
                                _movieListState.update {
                                    it.copy(popularMovieList = populerlist
                                    +populerlist.shuffled(),
                                        popularMovieListPages = movieListState.value.popularMovieListPages+1)


                                }
                            }
                        }
                    }
            }
        }
    }

    private fun getPopularmovieList(forceFechFromRemote:Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true )
            }
            movieListRepo.getMovieList(forcedfetchFormRemote = forceFechFromRemote
                ,Category.UPCOMING,
                _movieListState.value.upcomingMovieListPages).collectLatest {
                    result->
                when(result)
                {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let {
                                Upcominglist->
                            _movieListState.update {
                                it.copy(upcomingMovieList =Upcominglist
                                        +Upcominglist.shuffled(),
                                    upcomingMovieListPages = movieListState.value.upcomingMovieListPages+1)


                            }
                        }
                    }
                }
            }
        }
    }


}