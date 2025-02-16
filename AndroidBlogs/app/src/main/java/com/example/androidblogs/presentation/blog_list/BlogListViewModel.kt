package com.example.androidblogs.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidblogs.data.remote.HttpClientFectory
import com.example.androidblogs.data.remote.KtorRemoteBlogDataSource
import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.data.remote.toBlogList
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class BlogListViewModel:ViewModel() {

    private val  _state = MutableStateFlow(BlogListState())
    val state = _state
        .onStart {
        getAllBlog()
        }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _state.value)

    private val httpClient = HttpClientFectory.create(OkHttp.create())
    private val remoteSource = KtorRemoteBlogDataSource(httpClient)
    private fun getAllBlog() {
        viewModelScope.launch {
            _state.update {
                val blogDtos = remoteSource.getAllBlog()

                it.copy(blogs = blogDtos!!.toBlogList())
            }

        }
    }


}