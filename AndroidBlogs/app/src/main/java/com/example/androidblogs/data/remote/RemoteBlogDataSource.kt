package com.example.androidblogs.data.remote

import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.util.result

interface RemoteBlogDataSource {
    suspend fun getAllBlogs():result<List<BlogDto>>
    suspend fun fetchBlogContent(url:String):result<String>

}