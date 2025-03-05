package com.example.androidblogs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidblogs.data.remote.Contant.Blog_Table_Name

@Dao
interface BlogDao  {
    @Query("select * from blogs")
    suspend fun getAllBlogs():List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(blogs:List<BlogEntity>)

    @Query("select * from blogs where id=:BlogId")
    suspend fun getBlogById(BlogId:Int):BlogEntity?

    @Query("delete from blogs")
    suspend fun deleteAllBlogs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogContent(content:BlogContentEntity)

    @Query("select * from blogContent where blogId = :blogId")
    suspend fun getBlogContent(blogId:Int):BlogContentEntity?
}