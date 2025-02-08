package com.example.studysmart.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.studysmart.R
import com.example.studysmart.domain.model.Session

fun LazyListScope.StudySeccionsList(
    sectionTitle:String ,
    empltyString:String ,
    session:List<Session>,
    onClickIconDelete : (Session) -> Unit
)
{
    item {
        Text(sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(8.dp))
    }
    if(session.isEmpty())
    {
        item {
            Column(
                modifier = Modifier.fillMaxWidth() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.img_lamp) ,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(empltyString,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }


    }
    items(session){
        sessionitem->
        StudySessionCard(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            session = sessionitem,
            onClickDelete = {onClickIconDelete(sessionitem)})
    }
}

@Composable
private fun StudySessionCard(
    modifier : Modifier = Modifier ,
    session : Session ,
    onClickDelete :()->Unit

)
{
   Card(
        modifier = modifier
    ) {
        Row (
            modifier= Modifier.fillMaxWidth().padding(start = 4.dp) ,
            verticalAlignment = Alignment.CenterVertically
        ){

            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = session.relatedToSubject ,
                    maxLines = 1 ,
                    overflow = TextOverflow.Ellipsis ,
                    style = MaterialTheme.typography.titleMedium ,

                    )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "${session.data}" ,
                    style = MaterialTheme.typography.bodySmall
                )
            }
                Spacer(modifier = Modifier.weight(1f))
                Text("${session.duration} hr",
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = {onClickDelete()})
                {
                    Icon(
                        imageVector = Icons.Default.Delete ,
                        contentDescription = "" ,
                        )
                }

        }
    }

}