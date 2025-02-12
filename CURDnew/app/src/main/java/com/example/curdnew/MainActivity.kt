package com.example.curdnew

import android.os.Bundle
import android.util.Log
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
import com.example.curdnew.presentation.AppScreen
import com.example.curdnew.ui.theme.CURDNewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CURDNewTheme {
            AppScreen()

            }
        }
    }
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
        try {
            // Your code here
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception in onPause", e)
        }
    }
}

