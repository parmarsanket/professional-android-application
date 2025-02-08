package com.example.curd.presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun titleBox(modifier: Modifier,title:String,
             onValueChange: (String)->Unit,
             onSaveClick:()->Unit
             )
{

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = title,
                onValueChange = onValueChange,
                modifier = modifier.padding(horizontal = 8.dp),

                label = { Text("Enter title name") }
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                enabled = title.isNotEmpty(),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                onClick = {onSaveClick()
                          }, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            ) {

                Text(
                    "Save", modifier = Modifier.padding(horizontal = 12.dp)
                )
            }


        }


}