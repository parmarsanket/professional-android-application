package com.example.wildriftchampions.presentation.Components

import Champion
import ChampionResponse
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.wildriftchampions.R

@Composable
fun Champion_card(modifier: Modifier = Modifier,
                  champion: Champion)
{
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${champion.id}_0.jpg")
        .crossfade(true)
        .build()
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Row (){

            AsyncImage(
                placeholder = painterResource(R.drawable.noimg),
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier =Modifier
                    .clip(RectangleShape)
                    .width(100.dp)
                    .aspectRatio(9f/16f)
                    .clip(RoundedCornerShape(15.dp))

            )
            Column (modifier =Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
             Text(champion.name,maxLines =2 ,textAlign= TextAlign.Center,fontSize = 24.sp )
             Spacer(modifier = Modifier.height(8.dp))
             Text(champion.title, maxLines =2, overflow = TextOverflow.Ellipsis,fontSize = 14.sp
                     ,lineHeight = 17.sp,
                 textAlign = TextAlign.Center

             )
                Spacer(modifier = Modifier.height(8.dp))
                Text(champion.blurb, maxLines =3,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                )

            }
        }
    }

}

