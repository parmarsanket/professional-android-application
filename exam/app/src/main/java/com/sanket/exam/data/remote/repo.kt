package com.sanket.exam.data.remote

class myrepo:Apiservice
{
    val myobj = RetrofitFactory.createRetrofitInstance()
    val myapi = myobj.create(Apiservice::class.java)
    override suspend fun getmeals(): mydto {
       return myapi.getmeals()
    }

    override suspend fun getbyid(id: Int):MealsResponse {
        return myapi.getbyid(id)
    }


}