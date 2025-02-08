package com.example.studysmart.domain.model

import androidx.compose.ui.graphics.Color
import com.example.studysmart.ui.theme.Gradient10
import com.example.studysmart.ui.theme.Gradient6
import com.example.studysmart.ui.theme.Gradient7
import com.example.studysmart.ui.theme.Gradient8
import com.example.studysmart.ui.theme.Gradient9
import com.example.studysmart.ui.theme.gradient1
import com.example.studysmart.ui.theme.gradient2
import com.example.studysmart.ui.theme.gradient3
import com.example.studysmart.ui.theme.gradient4
import com.example.studysmart.ui.theme.gradient5

data class Subject(
    val name: String,
    val goalHour :Float,
    val Color : List<Color>,
    val SubID:Int
)
{
    companion object{
        val subjectColor = listOf(gradient1, gradient2, gradient3, gradient4, gradient5,
            Gradient6, Gradient7, Gradient8, Gradient9, Gradient10)
    }
}