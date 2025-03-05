package com.example.androidblogs.data.repository

import com.example.androidblogs.data.local.BlogContentEntity
import com.example.androidblogs.data.local.BlogDao
import com.example.androidblogs.data.local.toBlog
import com.example.androidblogs.data.local.toBlogList
import com.example.androidblogs.data.remote.RemoteBlogDataSource
import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.data.remote.toBlogEntityList
import com.example.androidblogs.data.remote.toBlogList
import com.example.androidblogs.domain.Blog
import com.example.androidblogs.domain.BlogRepository
import com.example.androidblogs.domain.util.result

class BlogRepositoryImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource,
    private val locatBlogDataSource : BlogDao
):BlogRepository {

    override suspend fun getAllBlogs(): result<List<Blog>> {
        val remoteBlogsresult =remoteBlogDataSource.getAllBlogs()
        return when(remoteBlogsresult)
        {
            is result.success -> {
                remoteBlogsresult.data?.let { blogs ->
                    locatBlogDataSource.deleteAllBlogs()
                    locatBlogDataSource.insertAllBlogs(blogs.toBlogEntityList())
                    result.success(data = blogs.toBlogList())
                } ?: result.error(message = "no data awalable")
            }


            is result.error -> {
                val localBlog = locatBlogDataSource.getAllBlogs()
                if(localBlog.isNotEmpty())
                {
                   result.error(
                       data = localBlog.toBlogList(),
                       message = remoteBlogsresult.message?:"we feild to fatch blog"
                   )
                }else{
                    result.error(
                        message = remoteBlogsresult.message?: "we feild to fatch blog and not catch data available"

                    )
                }
            }
        }
    }

    override suspend fun getBlogById(blogId: Int): result<Blog> {
        val blogEntity = locatBlogDataSource.getBlogById(blogId)
            ?:return result.error (message = "Blog not found in local databased.")
        val contentResult = remoteBlogDataSource.fetchBlogContent(blogEntity.contentUrl)

        return when(contentResult)
        {
            is result.error ->{
                val contentEntity = locatBlogDataSource.getBlogContent(blogId)
                if (contentEntity!=null)
                {

                    result.success(data = blogEntity.toBlog(contentEntity.content))
                }else{
                    result.error(message = "Feild to fetch content.${contentResult.message} ")

                }
            }
            is result.success -> {
                val blogContentEntity = BlogContentEntity(
                    blogId = blogId,
                    content =contentResult.data?:""
                )
                locatBlogDataSource.insertBlogContent(blogContentEntity)
                result.success(data = blogEntity.toBlog(contentResult.data))
            }
        }

    }
}