package com.example.androidblogs.presentation.blog_list.BlogContent

import com.example.androidblogs.domain.Blog

data class BlogContentState(
    val isLoading: Boolean = false,
    val errorMessage:String?=null,
    val blog: Blog?=null
)
