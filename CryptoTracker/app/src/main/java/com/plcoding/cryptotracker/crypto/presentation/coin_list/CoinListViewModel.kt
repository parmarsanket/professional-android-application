package com.plcoding.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.coin_detail.DataPoint
import com.plcoding.cryptotracker.crypto.presentation.model.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinListViewModel(
    private val coinDataSource:CoinDataSource
):ViewModel() {
     private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L),CoinListState())

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction)
    {
        when(action)
        {
            is CoinListAction.OnCoinClick -> {
                selectCoin(action.coinUi)

            }
            CoinListAction.OnRefresh -> {
                loadCoins()
            }
        }
    }
    private fun selectCoin(coinUi: CoinUi)
    {
        _state.update {
            it.copy(
                selectedCoin = coinUi
            )
        }
        viewModelScope.launch {
            coinDataSource
                .getCoinHistory(coinId = coinUi.id,
                    start = ZonedDateTime.now().minusDays(5),
                    end = ZonedDateTime.now()
                )
                .onSuccess { history ->
                    val datapoints = history.sortedBy { it.dataTime }
                        .map {DataPoint(
                            x= it.dataTime.hour.toFloat(),
                            y = it.priceUsd.toFloat(),
                            xLabel = DateTimeFormatter.ofPattern("ha\nM/d")
                                .format(it.dataTime)
                        ) }
                    _state.update {
                        it.copy(selectedCoin = it.selectedCoin?.copy(
                            coinPriceHistory = datapoints
                        ))
                    }

                }.onError {
                    error->
                    _events.send(CoinListEvent.Error(error))

                }
        }


    }

    private fun loadCoins(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading=true) }
            coinDataSource
                .getCoin()
                .onSuccess {
                    coin ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coin.map { it.toCoinUi() }
                        )
                    }

                }
                .onError {
                    error ->
                    _state.update { it.copy(isLoading=false) }
                    _events.send(CoinListEvent.Error(error))


                }


        }
    }

}