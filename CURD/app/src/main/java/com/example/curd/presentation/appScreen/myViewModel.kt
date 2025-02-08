package com.example.curd.presentation.appScreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.curd.Domain.model.Titile
import com.example.curd.Domain.usecase.getTitles
import com.example.curd.data.local.userTitile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class myViewModel @Inject constructor(
    private val gettitle: getTitles
):ViewModel() {
    private val _user = MutableStateFlow<List<Titile>>(emptyList())
    val title = _user.asStateFlow()

    var titleone = mutableStateOf(Titile( title = ""))

    init {
        viewModelScope.launch {
            gettitle().collect { userlist ->
                _user.value = userlist
            }
        }
    }

    fun getTilte(titile: userTitile) {
        viewModelScope.launch {
            gettitle.add(titile = titile)
        }
    }

    fun getDelete(titileid: Int) {
        viewModelScope.launch {
            gettitle.delete(titileid)
        }
    }

    fun getEdit(titile: userTitile) {


        viewModelScope.launch {
            gettitle.edit(titile = titile)
        }

    }
}
