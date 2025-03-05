package com.example.retrofitlearningapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitlearningapp.data.remote.dto.product
import com.example.retrofitlearningapp.data.repoimp.Repoimp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class myviewmodel @Inject constructor(
    private val repo : Repoimp
):ViewModel() {

    private val _products = MutableStateFlow<List<product>>(emptyList())
    val products: StateFlow<List<product>> = _products

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                _products.value = repo.getproduct()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}