package com.example.androidblogs.data.remote

import com.example.androidblogs.data.remote.Contant.GIT_URL
import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.Blog
import com.example.androidblogs.domain.util.result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import java.net.UnknownHostException

class KtorRemoteBlogDataSource(
    private val httpClient:HttpClient
) :RemoteBlogDataSource{
    override suspend fun getAllBlogs(): result<List<BlogDto>>
            {
                    return try {
                         val response = httpClient.get(urlString = GIT_URL)
                        val blog =response.body<List<BlogDto>>()
                        result.success(blog)
                    }
                    catch (e:UnknownHostException)
                    {

                        result.error(message = "no internet plz check $e")

                    }

                    catch (e: Exception)

                    {
                        e.printStackTrace()
                        result.error(message = "samathing want wroging $e")
                    }
            }

    override suspend fun fetchBlogContent(url: String): result<String> {
        return try {
            val response = httpClient.get(urlString = url)
            val blogContent =response.bodyAsText()
            result.success(blogContent)
        } catch (e:UnknownHostException)
        {

            result.error(message = "no internet plz check $e")

        } catch (e: Exception)

        {
            e.printStackTrace()
            result.error(message = "samathing want wroging $e")
        }
    }
}