package com.plcoding.cryptotracker.core.Navigation

import android.widget.Toast
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.core.presenttation.util.ObserverAsEvent
import com.plcoding.cryptotracker.core.presenttation.util.toSting
import com.plcoding.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListAction
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPanal(
    viewModel: CoinListViewModel= koinViewModel(),
    modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserverAsEvent(events = viewModel.events) {event->
        when(event)
        {
            is CoinListEvent.Error -> {
                Toast.makeText(context,event.error.toSting(context), Toast.LENGTH_SHORT).show()
            }
        }
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state=state,
                    onAction = {action->
                        viewModel.onAction(action)
                        when(action){
                            is CoinListAction.OnCoinClick->{
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }

                            CoinListAction.OnRefresh -> {}
                        }
                    }
                )
            }
        },
        detailPane ={
                CoinDetailScreen(state = state)
        } ,
        modifier = modifier

    )

}