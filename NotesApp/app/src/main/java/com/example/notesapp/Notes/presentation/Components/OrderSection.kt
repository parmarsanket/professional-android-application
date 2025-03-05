package com.example.notesapp.Notes.presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.Notes.util.NoteOrder
import com.example.notesapp.Notes.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder:NoteOrder=NoteOrder.Data(OrderType.Descending),
    onOrderChange:(NoteOrder)->Unit

) {
    Column (
        modifier = modifier
    ){
        Row( modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            DefaultRadioButton(
                modifier = Modifier,
                text = "Title",
                selected = noteOrder is NoteOrder.Title,
                onCheck ={
                    onOrderChange(NoteOrder.Title(noteOrder.orderType))
                }
            )
            Spacer(modifier =Modifier.width(8.dp))
            DefaultRadioButton(
                modifier = Modifier,
                text = "Date",
                selected = noteOrder is NoteOrder.Data,
                onCheck ={
                    onOrderChange(NoteOrder.Data(noteOrder.orderType))
                }
            )
            Spacer(modifier =Modifier.width(8.dp))
            DefaultRadioButton(
                modifier = Modifier,
                text = "Color",
                selected = noteOrder is NoteOrder.Color,
                onCheck ={
                    onOrderChange(NoteOrder.Color(noteOrder.orderType))
                }
            )



        }
        Spacer(modifier=Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth())
        {
            DefaultRadioButton(
                modifier = Modifier,
                text = "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending,
                onCheck ={
                    onOrderChange(noteOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier =Modifier.width(8.dp))
            DefaultRadioButton(
                modifier = Modifier,
                text = "Descending",
                selected = noteOrder.orderType is OrderType.Descending,
                onCheck ={
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                }
            )


        }
    }

}