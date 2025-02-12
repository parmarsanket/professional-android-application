package com.example.curdnew.presentation.Conponents

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Buttons(text:String, modifier: Modifier, onClick:()->Unit) {
    TextButton(onClick = {onClick()}, modifier = modifier, colors = ButtonDefaults.buttonColors()
    ) {
        Text(text)
    }

}