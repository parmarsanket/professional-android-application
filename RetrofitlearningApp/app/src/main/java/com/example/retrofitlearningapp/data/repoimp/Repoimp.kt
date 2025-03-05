package com.example.retrofitlearningapp.data.repoimp

import com.example.retrofitlearningapp.data.remote.dto.ApiService
import com.example.retrofitlearningapp.data.remote.dto.product
import org.jetbrains.annotations.ApiStatus
import javax.inject.Inject

class Repoimp @Inject constructor(
    private val aposervice: ApiService
) {
    suspend fun getproduct():List<product>
    {
        return aposervice.getProducts()
    }
}