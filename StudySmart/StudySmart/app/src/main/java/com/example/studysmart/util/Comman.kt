package com.example.studysmart.util

import android.service.quicksettings.Tile
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import com.example.studysmart.ui.theme.Green

enum class Priority(val title : String,val Color:Color,val value:Int)
{
    LOW(title="low", Color = Green, value = 0),
    MEDIUM(title="medium", Color = Yellow, value = 1),
    HIGH(title="high", Color = Red, value = 2);

    companion object{
        fun formInt(value:Int)= entries.firstOrNull{it.value==value}?:MEDIUM
    }

}

