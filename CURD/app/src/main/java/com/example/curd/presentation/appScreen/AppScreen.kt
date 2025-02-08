package com.example.curd.presentation.appScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import card
import com.example.curd.Domain.model.Titile
import com.example.curd.data.local.userTitile
import com.example.curd.presentation.Components.titleBox
import dagger.hilt.android.lifecycle.HiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreem( viewModel: myViewModel = hiltViewModel() )
{
    val titleslist by viewModel.title.collectAsState()

    var id by remember { mutableStateOf<Int?>(null) }
    var iseditopen by remember { mutableStateOf(false) }
    var isDeleteopen by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }

    if (iseditopen){
   EditDialog(
       title =  viewModel.titleone.value,
       onDismissRequest = {iseditopen=false},
       onConfirmation = {
           viewModel.getEdit(userTitile(viewModel.titleone.value.id,it))
           iseditopen=false

                        },

   )}

    if(isDeleteopen)
    {
        DeleteDialog(
            modifier = Modifier,
            onDismissRequest = {isDeleteopen=false},
            onConfirmation = {
                viewModel.getDelete(id!!)
                isDeleteopen = false}
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { apptopbar() },

    ) { it ->
        Column (
            modifier = Modifier.padding(it).fillMaxWidth()
        ) {


            titleBox(
                title = title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {title=it},
                onSaveClick = {
                    viewModel.getTilte(userTitile(title = title))
                    title = ""

                }
            )
            HorizontalDivider(modifier = Modifier.padding( 8.dp), thickness = 2.dp)

            LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 2.dp))
            {
               items(titleslist){
                   i->
                   card(i,
                       Modifier.padding(vertical = 2.dp),
                       onClickDelete = {ID->
                           id = ID
                           isDeleteopen=true
                       },
                       onClickEdit = {titles->
                           viewModel.titleone.value.id = titles.id
                           viewModel.titleone.value.title = titles.title
                           iseditopen=true},
                   )
               }


            }
        }
        }

    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun apptopbar()
{
    TopAppBar(
        title = { Text("CURD Operation")
        },
        colors = TopAppBarDefaults.topAppBarColors(titleContentColor = MaterialTheme.colorScheme.primary)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(

    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier

)
{
    AlertDialog(
        title = { Text("Delete Daigol") },
        onDismissRequest = {onDismissRequest()},
        text = {
            Text("Are sure you want to delete ? ")
        },
        confirmButton = {
            TextButton(onClick = {onConfirmation()}) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismissRequest()}) {
                Text("Cancel")
            }
        },
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDialog(
    title: Titile,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var editedTitle by remember { mutableStateOf(title.title) }

    AlertDialog(
        title = { Text("Edit Dialog") },
        text = {
            OutlinedTextField(
                value = editedTitle,
                onValueChange = { editedTitle = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
        },

        confirmButton = {
            Button(onClick = { onConfirmation(editedTitle) }, modifier = Modifier.fillMaxWidth(0.47f)) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest, modifier = Modifier.fillMaxWidth(0.47f)) {
                Text("Cancel")
            }
        },
        onDismissRequest = onDismissRequest,
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun App()
{
    MaterialTheme {
    AppScreem()
}
}