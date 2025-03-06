package com.example.moviesapp.movielist.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.movielist.presentation.Compodents.MovieItem
import com.example.moviesapp.movielist.presentation.MovieListUiEvent
import com.example.moviesapp.movielist.presentation.MyModel
import com.example.moviesapp.movielist.presentation.navigation.Route
import com.example.moviesapp.movielist.util.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomesScreen(navController: NavHostController) {
    val movieListViewModel = hiltViewModel<MyModel>()
    val movieListState = movieListViewModel.movieListState.collectAsState().value
    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MovieApp") }
            )
        },
        bottomBar = {
            Bottomnavigationbar(
                bottomNavHostController = bottomNavController,
                onEvent = movieListViewModel::onEvent
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(movieListState.popularMovieList.size) { index ->
                MovieItem(
                    movie = movieListState.popularMovieList[index],
                    navHostController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (index >= movieListState.popularMovieList.size - 1 && !movieListState.isLoading) {
                    movieListViewModel.onEvent(MovieListUiEvent.paginate(Category.POPULAR))
                }
            }
        }
    }
}
@Composable
fun Bottomnavigationbar(
    bottomNavHostController: NavHostController,
    onEvent:(MovieListUiEvent)->Unit
)
{
    val items = listOf(
        BottomItem(title = "Popular", icon = Icons.Rounded.Movie),
        BottomItem(title = "Upcooming", icon = Icons.Rounded.Upcoming    )
    )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        Row(modifier = Modifier) {
            items.forEachIndexed{
                index,bottonItem->
                NavigationBarItem(
                    selected = selected.intValue==index,
                    onClick = {
                        selected.intValue = index
                        when(selected.intValue)
                        {
                            0 ->{
                                onEvent(MovieListUiEvent.Navigate)
                                bottomNavHostController.popBackStack()
                                //bottomNavHostController.navigate()

                            }

                            1 ->{

                            }
                        }
                    },
                    icon = {
                        Icon(imageVector = bottonItem.icon,
                            contentDescription = null,
                            //tint = MaterialTheme.colorScheme.onBackground

                        )

                    }, label = {
                        Text(text = bottonItem.title,
                            //color = MaterialTheme.colorScheme.background
                        )
                    }
                )
            }
        }
    }

}
data class BottomItem(
    val title:String,
    val icon: ImageVector
)