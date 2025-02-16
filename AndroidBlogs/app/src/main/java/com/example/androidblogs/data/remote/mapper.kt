package com.example.androidblogs.data.remote

import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.Blog

fun BlogDto.toBlog() = Blog(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    contantUrl = contentUrl,
    contant = null
)

fun List<BlogDto>.toBlogList()= map{it.toBlog()}