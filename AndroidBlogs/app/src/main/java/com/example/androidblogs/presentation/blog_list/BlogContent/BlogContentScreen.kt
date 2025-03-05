package com.example.androidblogs.presentation.blog_list.BlogContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun BlogContentScreen(
    modifier: Modifier =   Modifier,
    onBlackButtonClick:()->Unit,
    state: BlogContentState

) {
    Column (
        modifier=modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
    {
        BlogContentTopbar(
            title = state.blog?.title,
            onBlackButtonClick = onBlackButtonClick
        )

        MainContent(
            modifier=Modifier.fillMaxSize()
                //.verticalScroll(rememberScrollState())
                .padding(15.dp),
            blogContent =state.blog?.contant
        )
       



    }
}

@Composable
private fun MainContent(modifier: Modifier = Modifier,blogContent:String?) {
    Column (
        modifier = modifier
    ){
        MarkdownText(
            markdown = blogContent?:"this is a content Screen",
            linkColor = MaterialTheme.colorScheme.secondary,
            isTextSelectable = true,
            syntaxHighlightColor = MaterialTheme.colorScheme.surfaceVariant,
            syntaxHighlightTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentTopbar(modifier: Modifier = Modifier,
                      title:String?,

                      onBlackButtonClick: () -> Unit) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = { Text(title?: "Blog Content ",
            overflow = TextOverflow.Ellipsis,
            maxLines = 1) },
        navigationIcon =  {
            IconButton(onClick = {onBlackButtonClick()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )

}