package com.example.studysmart.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysmart.domain.model.Session
import com.example.studysmart.presentation.components.DeleteDialog
import com.example.studysmart.presentation.components.StudySeccionsList
import com.example.studysmart.presentation.components.SubjectListBottomSheet
import com.example.studysmart.presentation.components.taskList
import com.example.studysmart.session
import com.example.studysmart.subjects
import com.example.studysmart.tasks
import kotlinx.coroutines.launch
//@Destination
@Composable
fun SessionScreenRoute()
{
    SessionScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SessionScreen()
{
    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetopen by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var isdeleteDialogopen by rememberSaveable{ mutableStateOf(false) }


    SubjectListBottomSheet(
        sheetState =sheetState ,
        isopen =  isBottomSheetopen,
        subjects = subjects ,
        onSubjectClick ={
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) isBottomSheetopen=false
            }
        } ,
        onDismissRequest ={isBottomSheetopen=false} ,

        )
    DeleteDialog(
        isOpen = isdeleteDialogopen,
        title = "Delete Session",
        bodyText = "Are you sure, you want to delete this task?" +
                "this action cant be undone.",
        onConformation = {isdeleteDialogopen=false},
        onDissmissRequest = {isdeleteDialogopen=false}
    )
    Scaffold(
        topBar = {
            Sessiontopbar  (
                onBackButtinClick = {}
            )
        }

    ){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it))
            {
                item { timerSesson(modifier =Modifier.fillMaxWidth().aspectRatio(1f))  }
                item { RelatedToSubjectSection(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    relatedToSubject = "English",
                    selectedSubjectButtonClick ={
                        isBottomSheetopen=true
                    }
                ) }
                item { buttionSectiom(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    startButtionclick = {},
                    cancelButtionclick = {},
                    finishedButtiomClick ={}
                ) }
                StudySeccionsList(
                    sectionTitle = "RECENT STUDY HISTORY",
                    empltyString = "YOU dont have any recent session. \n " +
                            "Start a study session to begin recording your progress ",
                    session = session ,
                    onClickIconDelete = {
                        isdeleteDialogopen=true
                    }
                )

            }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Sessiontopbar(
    onBackButtinClick:()->Unit,
    ) {
    TopAppBar(
        navigationIcon = {
           IconButton(onClick = {onBackButtinClick()}) {

               Icon(imageVector =Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
           }
                         },
        title ={ Text(text ="Study Session", style = MaterialTheme.typography.headlineSmall) })

}
@Composable
private fun timerSesson(modifier : Modifier){
    Box (modifier=modifier,
    contentAlignment = Alignment.Center){
        Box (modifier = Modifier
            .size(250.dp)
            .border(5.dp,MaterialTheme.colorScheme.surfaceVariant, CircleShape))
        {
        }
            Text("00:05:32",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp))
    }
}

@Composable
private fun RelatedToSubjectSection(
   modifier : Modifier,
   relatedToSubject:String,
   selectedSubjectButtonClick:()->Unit
){
    Column(modifier = modifier){

        Text(relatedToSubject, style = MaterialTheme.typography.bodySmall)
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(relatedToSubject, style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = {selectedSubjectButtonClick()}) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }

        }

    }
}
@Composable
private fun buttionSectiom(
    modifier : Modifier,
    startButtionclick:()->Unit,
    cancelButtionclick:()->Unit,
    finishedButtiomClick:()->Unit
){
    Row (modifier = modifier
        , horizontalArrangement = Arrangement.SpaceBetween)
    {
        Button(onClick = cancelButtionclick) { Text(
            "Cancel",modifier=Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) }
        Button(onClick = startButtionclick) { Text(
            "Start",modifier=Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) }
        Button(onClick = finishedButtiomClick) { Text(
            "Finish",modifier=Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) }
    }

}

@Composable
@Preview(showBackground = true)
fun showsessom(){
    SessionScreen()
}