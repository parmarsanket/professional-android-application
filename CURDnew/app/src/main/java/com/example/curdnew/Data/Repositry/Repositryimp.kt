package com.example.curdnew.Data.Repositry

import com.example.curdnew.Data.Local.Title
import com.example.curdnew.Data.Local.TitleDao
import com.example.curdnew.Module.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repositryimp @Inject constructor(private val titleDao: TitleDao):Repository {
    override suspend fun getDelete(title: Title) {
            titleDao.deleteTitle(title)
    }

    override suspend fun AddEdit(title: Title) {
            titleDao.upsertTitle(title)
    }

    override fun getAllTitles(): Flow<List<Title>> {
             return   titleDao.getAllTitle()

    }

}