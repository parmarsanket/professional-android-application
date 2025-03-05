package com.example.shppingapp.presentation.compodents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shppingapp.R

@Composable
fun CustomButton(
    name : String,
    modifier: Modifier= Modifier,
    enable : Boolean =true,
    onClick :()->Unit,
    color: Color =  colorResource(R.color.orenge),
    contentColor :Color = colorResource(R.color.white)
) {
    Button(onClick = onClick,
        enabled = enable ,
        colors =  ButtonDefaults.buttonColors(backgroundColor = color,
            contentColor = contentColor),
        modifier = modifier.clip(RoundedCornerShape(5.dp))
    ){
        Text(name, modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
            textAlign = TextAlign.Center, color = contentColor)
    }
}