package com.example.notesapp.Notes.domain.model

import android.transition.Explode
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.ui.theme.BabyBlue
import com.example.notesapp.ui.theme.LightBlue
import com.example.notesapp.ui.theme.LightGreen
import com.example.notesapp.ui.theme.RedOrange
import com.example.notesapp.ui.theme.RedPink
import com.example.notesapp.ui.theme.Violet

@Entity(tableName = "note")
data class Note(
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey
    val id:Int?=null
){
    companion object{
        val notColors :List<Color> = listOf(RedOrange, LightGreen, LightBlue, Violet, BabyBlue,
            RedPink)
    }
}
class InvalidNoteExcetion(message:String):Exception(message)