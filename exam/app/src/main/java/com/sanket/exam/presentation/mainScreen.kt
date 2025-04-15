package com.sanket.exam.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sanket.exam.R
import com.sanket.exam.data.remote.Apiservice
import com.sanket.exam.data.remote.Meal
import com.sanket.exam.data.remote.MealsResponse
import com.sanket.exam.data.remote.RetrofitFactory
import com.sanket.exam.data.remote.myMeal
import com.sanket.exam.data.remote.myrepo

@Composable
fun mainScreen(modifier: Modifier)
{


    val mylist = myrepo()
    val myrepo by produceState<List<Meal>>(initialValue = emptyList()) {
        value = mylist.getmeals().meals
    }
    val mymealid by produceState<List<myMeal>>(initialValue = emptyList()) {
        value = mylist.getbyid(52772).meals
    }

    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        state = rememberLazyGridState(),
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 100.dp, bottom = 100.dp)
    ) {
        items(myrepo){
            mycomposeble(it)
        }


    }
//    if (mymealid.isNotEmpty()) {
//        Text(text = "Meal ID: ${mymealid.first().idMeal}")
//        Text(text = "Meal Name: ${mymealid.first().strMeal}")
//    } else {
//        Text("Loading meal details...")
//    }


}
@Composable
fun mycomposeble(it: Meal) {

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = it.strMealThumb,
            contentDescription = ""
        )
        Text(text = it.strMeal)
    }

}
