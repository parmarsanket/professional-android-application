package com.example.curdnew.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curdnew.Data.Local.Title
import com.example.curdnew.Data.Repositry.Repositryimp
import com.example.curdnew.Module.Repository
import com.example.curdnew.presentation.Conponents.AppEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.annotation.meta.When
import javax.inject.Inject

@HiltViewModel
class myviewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(AppState())

    val state = combine(
        _state,
        repository.getAllTitles()
    ) { state, titles ->
        state.copy(
            titles = titles
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue =AppState()
    )
    fun onEvent(Event:AppEvent)
    {
        when(Event){
            AppEvent.delete -> deleteTitle()
            AppEvent.saveEdit -> addEdit()
            is AppEvent.OnSubjectNameChange ->{
_state.update {
        it.copy(title = Event.name)
        }
            }

            is AppEvent.OnClick -> {
                _state.update {
                    it.copy(title = Event.title.title,
                        id = Event.title.id)
                }
            }
        }
    }

    private fun deleteTitle() {
viewModelScope.launch {

            repository.getDelete(
                    title = Title(
                            title = state.value.title,
                        id = state.value.id
                    )
            )
    _state.update {
        it.copy(
            title = "",
            id = null
        )
    }
}
    }

    private fun addEdit() {
        viewModelScope.launch {

            repository.AddEdit(
                title = Title(
                    title = state.value.title,
                    id = state.value.id
                )
            )
            _state.update {
it.copy(
    title = "",
    id = null
)
            }
        }
    }
}