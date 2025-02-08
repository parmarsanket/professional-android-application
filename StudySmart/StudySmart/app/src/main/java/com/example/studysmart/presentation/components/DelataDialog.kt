package com.example.studysmart.presentation.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class , ExperimentalLayoutApi::class)
@Composable
fun DeleteDialog(
    onDissmissRequest:()->Unit ,
    onConformation:()->Unit ,
    bodyText:String,
    title:String,
    isOpen:Boolean
) {

    if (isOpen)
    {
        AlertDialog(
            dismissButton = { TextButton(onClick = onDissmissRequest) { Text("cancel") } } ,
            title = { Text(text = title) } ,
            text = {
               Text(bodyText)
            } ,
            confirmButton = {
                TextButton(onClick = onConformation) {
                    Text("Delete") } } ,
            onDismissRequest = onDissmissRequest
        )

    }

}