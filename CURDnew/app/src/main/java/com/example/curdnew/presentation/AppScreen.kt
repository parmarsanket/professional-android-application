package com.example.curdnew.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import com.example.curdnew.Data.Local.Title
import com.example.curdnew.presentation.Conponents.AppEvent
import com.example.curdnew.presentation.Conponents.Buttons
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@Composable
fun AppScreen()
{

     val viewModel: myviewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val  onEvent = viewModel::onEvent
    var iseditopen by remember { mutableStateOf(false) }


    if(iseditopen)
    {
    editAddBox(
        titleBox ="edit",
        title = Title(state.title,state.id),
        onValueChange = {onEvent(AppEvent.OnSubjectNameChange(it))},
        onDismissRequest = {iseditopen=false},
        dismissButton = {iseditopen=false},
        confirmButton = {
            onEvent(AppEvent.saveEdit)
            iseditopen=false}
    )
    }
    Scaffold(
        topBar = { topbar() },
        floatingActionButton = { floatingbtn(onClick = {iseditopen=true}) },
    ) {
       LazyColumn(modifier = Modifier.padding(it).fillMaxSize()) {
           item {         HorizontalDivider(thickness = 3.dp, modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp))
           }
           items(state.titles){i->
               cardBox(i, modifier = Modifier.fillParentMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp),
                   onEdit = {onEvent(AppEvent.OnClick(i))
                       iseditopen=true},
                   onDelete = {onEvent(AppEvent.OnClick(i))
                   onEvent(AppEvent.delete)}
               )



           }

       }
    }
}

@Composable
fun itemsbox(title: Title,modifier: Modifier,onValueChange:(String)->Unit) {

    Column(modifier=modifier) {
        OutlinedTextField(
            value = title.title,
            onValueChange = {onValueChange(it)},
            modifier = modifier.padding(8.dp),
            placeholder = { Text("Enter Title here")
            }, colors = OutlinedTextFieldDefaults.colors()
        )

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topbar()
{
    TopAppBar(
        title = { Text("CURD App") },
        colors =  TopAppBarDefaults.topAppBarColors()
    )
}

@Composable
fun floatingbtn(onClick :()->Unit)
{
    FloatingActionButton(
        onClick = {onClick()},
        shape = RoundedCornerShape(10.dp),
    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = ""
        )
    }
}
@Composable
fun cardBox(title: Title,modifier: Modifier,onEdit:()->Unit,onDelete:()->Unit)
{
    ElevatedCard(modifier = modifier) {
        Row (modifier = modifier.padding(horizontal = 15.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = title.title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp,),
                )
        Row {

            Image(
                imageVector = Icons.Default.Edit,
                contentDescription = "",
                alignment = Alignment.CenterEnd,
                modifier = Modifier.clickable {onEdit () }
            )
            Image(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                alignment = Alignment.CenterEnd,
                modifier = Modifier.clickable {  onDelete()}
                )
        }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editAddBox(
    titleBox:String,
    title: Title,
    onDismissRequest: () -> Unit,
    dismissButton: () -> Unit,
    confirmButton:()->Unit,
    onValueChange:(String)->Unit

)
{
    AlertDialog(
        title = { Text(titleBox) },
        text = {
            itemsbox(title, modifier = Modifier.fillMaxWidth(), onValueChange =onValueChange )
        },
        onDismissRequest = {onDismissRequest()},
        dismissButton = { Buttons("Cancel", modifier = Modifier.fillMaxWidth(0.47f), onClick = {dismissButton()})  },
        modifier = Modifier,
        confirmButton = { Buttons("Save", modifier = Modifier.fillMaxWidth(0.47f), onClick = {confirmButton()})  },

    )
}


