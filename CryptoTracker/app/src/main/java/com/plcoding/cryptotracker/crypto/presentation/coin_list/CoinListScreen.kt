package com.plcoding.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.plcoding.cryptotracker.core.presenttation.util.toSting
import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.CointListItem
import com.plcoding.cryptotracker.crypto.presentation.model.CoinUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
    onAction: (CoinListAction)->Unit
    //events: Flow<CoinListEvent>
){



    if(state.isLoading){
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }

    }else {
        LazyColumn (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(state.coins){
                coinUi->
                CointListItem(
                    coinUI = coinUi,
                    onClick = {onAction(CoinListAction.OnCoinClick(coinUi))},
                    modifier = Modifier.fillMaxSize()
                )
                HorizontalDivider()
            }
        }
    }

}