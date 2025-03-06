package com.example.moviesapp.movielist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.movielist.presentation.Screens.HomesScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
   NavHost(
       startDestination = Route.HomesScreen,
       modifier = modifier,
       navController = navController
   ) {
       composable<Route.HomesScreen> {
                HomesScreen(navController = rememberNavController())
       }


       composable<Route.MovieScreen> {  }

   }
}