package com.example.androidblogs.di

import com.example.androidblogs.data.local.BlogDatabase
import com.example.androidblogs.data.local.DataBaseFactry
import com.example.androidblogs.data.remote.HttpClientFectory
import com.example.androidblogs.data.remote.KtorRemoteBlogDataSource
import com.example.androidblogs.data.remote.RemoteBlogDataSource
import com.example.androidblogs.data.repository.BlogRepositoryImpl
import com.example.androidblogs.domain.BlogRepository
import com.example.androidblogs.presentation.blog_list.BlogContent.BlogContentScreen
import com.example.androidblogs.presentation.blog_list.BlogContent.BlogContentViewModel
import com.example.androidblogs.presentation.blog_list.BlogListViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind

import org.koin.dsl.module
import kotlin.math.sin

val koinMainModule = module {

    single { DataBaseFactry.create( get()) }
    single { get <BlogDatabase>().blogDao() }

    single {
        HttpClientFectory.create(OkHttp.create())
    }
    singleOf(::KtorRemoteBlogDataSource).bind<RemoteBlogDataSource>()
    singleOf(::BlogRepositoryImpl).bind<BlogRepository>()
    viewModelOf(::BlogListViewModel)
    viewModelOf(::BlogContentViewModel)
}