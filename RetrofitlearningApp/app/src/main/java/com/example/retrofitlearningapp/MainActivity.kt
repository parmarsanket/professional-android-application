package com.example.retrofitlearningapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.retrofitlearningapp.data.remote.dto.product
import com.example.retrofitlearningapp.presentation.myviewmodel
import com.example.retrofitlearningapp.ui.theme.RetrofitLearningAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productViewModel: myviewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitLearningAppTheme {

                    ProductScreen(productViewModel)
            }
        }
    }
    @Composable
    fun ProductScreen(viewModel: myviewmodel) {
        val products by viewModel.products.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.fetchProducts() // Call API only once
        }

        LazyColumn(modifier = Modifier.safeContentPadding()) {
            items(products) { product ->
                Text(text = product.category)
            }
        }
    }

}

@HiltAndroidApp
class MyApp : Application()
