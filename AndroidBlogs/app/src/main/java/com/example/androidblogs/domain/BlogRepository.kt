package com.example.androidblogs.domain

import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.util.result

interface BlogRepository {
    suspend fun getAllBlogs(): result<List<Blog>>
    suspend fun getBlogById(blogId:Int): result<Blog>

}