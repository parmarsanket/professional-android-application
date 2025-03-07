package com.example.notesapp.Notes.presentation.Components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.Notes.NotesViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.Notes.NotesEvent
import com.example.notesapp.Notes.domain.model.Note
import com.example.notesapp.Notes.util.NoteOrder

@Composable
fun NoteScreen(

    modifier: Modifier = Modifier,

    viewModel:NotesViewModel =  hiltViewModel()

) {
    val notes = listOf(
        Note("my notes1","this my content",1,1234),
        Note("my notes1","this my content",1,12216),
        Note("my notes1","this my content",1,0),
        Note("my notes1","this my content",1,1),
        Note("my notes1","this my content",1,223),
        Note("my notes1","this my content",1,220),
        Note("my notes1","this my content",1,120),
        Note("my notes1","this my content",1,23),
        Note("my notes1","this my content",1,100)
    )

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {

            },
               ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        Column (modifier=Modifier.fillMaxSize().padding(it).padding(16.dp)){
            Row (modifier=Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically)
            {
                 Text("Your Notes", style = MaterialTheme.typography.headlineMedium)
                IconButton(onClick = {
                    viewModel.onEvent(NotesEvent.ToggleOrderSection)
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }

            }
            AnimatedVisibility(
                visible= state.isOrderSectionVisible,
                enter = fadeIn()+ slideInVertically(),
                exit = fadeOut()+ slideOutVertically()
            ){
                OrderSection (
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    }

                    )
            }
            Spacer(modifier=Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                items(state.notes)
//                {
//
//                    NoteItem(
//                        note=it,
//                        modifier = Modifier.fillMaxWidth().clickable {  },
//                        onDeleteClick = {viewModel.onEvent(NotesEvent.DeleteNote(note = it))}
//                        )
//                    Spacer(modifier=Modifier.height(16.dp))
//                }
                items(notes)
                {

                    NoteItem(
                        note=it,
                        modifier = Modifier.fillMaxWidth().clickable {  },
                        onDeleteClick = {viewModel.onEvent(NotesEvent.DeleteNote(note = it))}
                        )
                    Spacer(modifier=Modifier.height(16.dp))
                }


            }
        }
    }

}