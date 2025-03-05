package com.example.androidblogs.data.local

import com.example.androidblogs.domain.Blog

fun BlogEntity.toBlog(
    content:String? = null
)= Blog(
id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    contantUrl = contentUrl,
    contant = content

)

fun List<BlogEntity>.toBlogList() = map { it.toBlog() }