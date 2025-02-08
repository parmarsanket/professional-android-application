package com.example.studysmart.presentation.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.core.graphics.Insets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDatepicker(
    state:DatePickerState,
    isopen:Boolean,
    coformtext:String="ok",
    dismisstxt:String="cancel" ,
    onDissmissButtionClicked : () -> Unit ,
    onConfirmButtionClicked : () -> Unit,


) {
   if (isopen){
       DatePickerDialog(

           onDismissRequest = {onDissmissButtionClicked()},
           confirmButton = {
               TextButton( onClick = onConfirmButtionClicked)
               {
                   Text(coformtext)
               }
           },
           dismissButton = {
               TextButton( onClick =onConfirmButtionClicked)
               {
                   Text(dismisstxt)
               }
           },
           content = {
               DatePicker(
                   state=state,

               )
           }

       )

   }

}