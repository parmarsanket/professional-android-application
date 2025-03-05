package com.example.shppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shppingapp.presentation.HomeScreen
import com.example.shppingapp.presentation.LoginScreen
import com.example.shppingapp.presentation.SignUpScreen
import com.example.shppingapp.ui.theme.ShppingAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShppingAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   NavHost(
                       modifier = Modifier.padding(innerPadding),
                       navController = navController,
                       startDestination = ScreenA
                   ){
                       composable<ScreenA> {
                           LoginScreen(navController = navController)
                       }
                       composable<ScreenB> {
                           SignUpScreen(navController = navController)
                       }
                       composable<ScreencC> {
                           val arg  = it.toRoute<ScreencC>()
                           HomeScreen(arg.string,navController = navController)
                       }
                   }
                }
            }
        }
    }
}
@Serializable
object ScreenA

@Serializable
object ScreenB

@Serializable
data class ScreencC(val string: String)

