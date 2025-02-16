package com.example.androidblogs.presentation.blog_list.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageResult
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.androidblogs.R
import com.example.androidblogs.domain.Blog

@Composable
fun BlogCard(modifier: Modifier = Modifier,blog: Blog) {
    Card (modifier = modifier)
    {
        BlogCardImage(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f),blog.thumbnailUrl)

        Text(blog.title,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
private fun BlogCardImage(modifier: Modifier = Modifier,imageUrl:String) {
    Box(modifier=modifier)
    {
        val context = LocalContext.current
        val imagerequest = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build()
                    AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imagerequest,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(R.drawable.img)  ,
            error = painterResource(R.drawable.img)  ,
        )
    }
    
}

