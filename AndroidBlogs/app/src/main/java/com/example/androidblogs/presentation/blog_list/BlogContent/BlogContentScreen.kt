package com.example.androidblogs.presentation.blog_list

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidblogs.presentation.navigation.Route

@Composable
fun BlogContentScreen(
    modifier: Modifier =   Modifier,
    onBlackButtonClick:()->Unit,

) {
    Column () {
        BlogContentTopbar(
onBlackButtonClick = onBlackButtonClick
        )


        Text("this is a content Screen")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentTopbar(modifier: Modifier = Modifier,

                      onBlackButtonClick: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = { Text("Blog Content ") },
        navigationIcon =  {
            IconButton(onClick = {onBlackButtonClick()}) {
                Icon(
                    imageVector =Icons.Default.ArrowBack ,
                    contentDescription = null
                )
            }
        }
    )

}