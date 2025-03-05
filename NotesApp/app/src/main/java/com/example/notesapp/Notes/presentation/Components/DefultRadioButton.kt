package com.example.notesapp.Notes.presentation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    modifier: Modifier = Modifier,
    text:String,
    selected:Boolean,
    onCheck :()->Unit
) {
    Row(modifier=modifier){
        RadioButton(selected = selected, onClick ={ onCheck() },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor =   MaterialTheme.colorScheme.onBackground
            ))
        Spacer(modifier=Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }

}