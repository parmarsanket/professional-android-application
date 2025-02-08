package com.example.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import com.example.studysmart.ui.theme.StudySmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {



            }
        }
    }
}
val subjects = listOf(
    Subject("English", goalHour = 23.4f ,  Subject.subjectColor[0],0) ,
    Subject("Python", goalHour = 23.4f ,  Subject.subjectColor[1],0) ,
    Subject( "Maths", goalHour = 23.4f ,  Subject.subjectColor[2],0) ,
    Subject("Android", goalHour = 23.4f ,  Subject.subjectColor[3],0) ,
    Subject(".NET", goalHour = 23.4f ,  Subject.subjectColor[4],0) ,
    Subject("C++", goalHour = 23.4f ,  Subject.subjectColor[5],0)

)
val tasks = listOf(
    Task("pereare exam", description = "", duoDate = 0L, priority = 0, relatedToSubject ="",
        isComplete = false,0,0 ) ,
    Task("pereare exam", description = "", duoDate = 0L, priority = 1, relatedToSubject ="",
        isComplete = true ,0,0) ,
    Task("pereare c++", description = "", duoDate = 0L, priority = 2, relatedToSubject ="",
        isComplete = false,0,0 ) ,
    Task("pereare nots", description = "", duoDate = 0L, priority = 3, relatedToSubject ="",
        isComplete = true ,0,0) ,
    Task("projects exam", description = "", duoDate = 0L, priority = 4, relatedToSubject ="",
        isComplete = false,0,0 ) ,
)
val session = mutableListOf(
    Session(relatedToSubject = "English", data = 0L, duration = 2, sessionID = 0,
        sessionSubjectID = 0) ,
    Session(relatedToSubject = "C++", data = 0L, duration = 2, sessionID = 0,
        sessionSubjectID = 0) ,
    Session(relatedToSubject = "Maths", data = 0L, duration = 2, sessionID = 0,
        sessionSubjectID = 0) ,
    Session(relatedToSubject = "Android", data = 0L, duration = 2, sessionID = 0,
        sessionSubjectID = 0) ,
    Session(relatedToSubject = ".Net", data = 0L, duration = 2, sessionID = 0,
        sessionSubjectID = 0) ,
    Session(relatedToSubject = "Python", data = 0L, duration = 2, sessionID = 0,
        sessionSubjectID = 0) ,
)


