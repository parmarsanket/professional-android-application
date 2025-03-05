package com.example.androidblogs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidblogs.presentation.blog_list.BlogContent.BlogContentScreen
import com.example.androidblogs.presentation.blog_list.BlogContent.BlogContentViewModel
import com.example.androidblogs.presentation.blog_list.BlogListScreen
import com.example.androidblogs.presentation.blog_list.BlogListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.BlogScreen
    ){
        composable<Route.BlogScreen> {
            val viewModel  = koinViewModel<BlogListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            BlogListScreen(
                modifier = Modifier,
                state = state,
                event = viewModel.events,
                onBlogCardClick = {
                    navController.navigate( Route.BlogContentScreen(it))
                }
            )
        }
        composable<Route.BlogContentScreen> {
            val viewModel= koinViewModel<BlogContentViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
                BlogContentScreen(
                    state = state,
               onBlackButtonClick = {

                   navController.navigateUp()
               }
           )
        }

    }
}