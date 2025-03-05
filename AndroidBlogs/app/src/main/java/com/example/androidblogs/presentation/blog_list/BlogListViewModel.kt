package com.example.androidblogs.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidblogs.domain.BlogRepository
import com.example.androidblogs.domain.util.result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogListViewModel(
    private val blogRepository: BlogRepository
):ViewModel() {


    private val  _state = MutableStateFlow(BlogListState())
    val state = _state
        .onStart {
        getAllBlog()
        }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _state.value)

    private val _events = Channel<BlogListEvent>()
    val  events = _events. receiveAsFlow()

    private fun getAllBlog() {
        viewModelScope.launch {
            val result = blogRepository.getAllBlogs()
            when(result){
                is result.success ->
                {
                    _state.update {
                        it.copy(blogs = result.data.orEmpty(),
                            errorMessage = null)

                    }
                }
                is result.error ->
                {
                    _state.update {
                        it.copy(blogs = result.data.orEmpty(),
                            errorMessage = result.message)

                    }
                    result.message?.let {
                        _events.send(BlogListEvent.Error(error = it))
                    }
                }
            }
        }
    }


}