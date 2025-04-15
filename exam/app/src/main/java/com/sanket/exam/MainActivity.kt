package com.sanket.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sanket.exam.presentation.mainScreen
import com.sanket.exam.ui.theme.ExamTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(title = { Text("my google map api", modifier = Modifier.fillMaxWidth()) }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue
                    ),
                        modifier = Modifier.background(Color.Red)) },
                    bottomBar = {
                        Row (modifier = Modifier.fillMaxWidth().navigationBarsPadding().padding(vertical = 8.dp)
                            .background(color = Color.Red),
                            horizontalArrangement = Arrangement.SpaceAround){
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "", modifier = Modifier.clickable {  }.size(50.dp))
                            Icon(imageVector = Icons.Default.Search, contentDescription = "", modifier = Modifier.clickable {  }.size(50.dp))
                            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.clickable {  }.size(50.dp))

                        }
                    }


                ) { innerPadding ->
                    mainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

