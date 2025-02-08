package com.example.curd.Domain.usecase

import com.example.curd.Domain.model.Titile
import com.example.curd.data.local.userTitile
import com.example.curd.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class getTitles(private val repository: Repository) {
 operator fun invoke():Flow<List<Titile>>{
     return repository.getAllTilte().map {
         entities->
         entities.map { entity->Titile(entity.id,entity.title) }
     }
 }
    suspend fun add(titile: userTitile)
    {
        repository.adduse(titile)
    }
    suspend fun delete(titileid: Int)
    {
        repository.deletetitle(titileid)
    }
    suspend fun edit(titile: userTitile)
    {
        repository.Editbyid(titile)

    }

}