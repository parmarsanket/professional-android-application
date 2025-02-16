package com.example.androidblogs.data.remote

import com.example.androidblogs.data.remote.Contant.GIT_URL
import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.Blog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorRemoteBlogDataSource(
    private val httpClient:HttpClient
) {
            suspend fun getAllBlog():List<BlogDto>?
            {
                    return try {
                         val response = httpClient.get(urlString = GIT_URL)
                        response.body<List<BlogDto>>()
                    }catch (e: Exception)
                    {
                        e.printStackTrace()
                        null

                    }
            }
}