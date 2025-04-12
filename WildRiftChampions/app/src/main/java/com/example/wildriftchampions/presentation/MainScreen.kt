package com.example.wildriftchampions.presentation

import Champion
import ChampionResponse
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wildriftchampions.data.repoimp.Repoimp
import com.example.wildriftchampions.presentation.Components.Champion_card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier) {
    val repoimp = Repoimp()

    val champions by produceState<List<Champion>>(initialValue = emptyList()) {
        value = repoimp.getchamp().data.map { it.value }
    }

    Scaffold (
        topBar = { TopAppBar(
            title = {
                Text("Wild Rift Champions", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        ) }

    ){

        LazyColumn (modifier = modifier.padding(it)){



            items(champions)
            {
                    Champion_card(modifier = Modifier.fillMaxWidth(), champion = it)
            }
        }
    }

}