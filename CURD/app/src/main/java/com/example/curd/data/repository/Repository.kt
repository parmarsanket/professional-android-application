package com.example.curd.data.repository

import com.example.curd.data.local.TitleDao
import com.example.curd.data.local.userTitile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val titleDao: TitleDao) {

    fun getAllTilte ():Flow<List<userTitile>> = titleDao.getAllTitlelist()

    suspend fun adduse(titile: userTitile )
    {
        titleDao.insertTilte(titile)
    }
    suspend fun deletetitle(titileid: Int)
    {
        titleDao.DeletebyId(titileid)
    }
    suspend fun Editbyid(titile: userTitile)
    {
         titleDao.Edit(titile)
    }
}