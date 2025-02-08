package com.example.studysmart.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysmart.R
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.presentation.components.AddSubjectDialog
import com.example.studysmart.presentation.components.Countcard
import com.example.studysmart.presentation.components.DeleteDialog
import com.example.studysmart.presentation.components.StudySeccionsList
import com.example.studysmart.presentation.components.SubjectCard
import com.example.studysmart.presentation.components.taskList
import com.example.studysmart.session
import com.example.studysmart.subjects
import com.example.studysmart.tasks

@Composable
//@Destination(start = true)
fun DashboardScreenRoute()
{
    DashboradScrren()

}


@Composable
private fun DashboradScrren() {

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHour by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectColor.random()) }
    var isdeletesessionOpen by rememberSaveable { mutableStateOf(false) }


    AddSubjectDialog(isOpen = isAddSubjectDialogOpen ,
        onConformation = { isAddSubjectDialogOpen = false } ,
        onDissmissRequest = { isAddSubjectDialogOpen = false } ,
        selectedColor = selectedColor ,
        onColorChnage = { selectedColor = it } ,
        subjectName = subjectName ,
        goalHour = goalHour ,
        onSubjectNameChnage = { subjectName = it } ,
        onGoalNameChnage = { goalHour = it }

    )
    DeleteDialog(isOpen = isdeletesessionOpen ,
        title = "Delete Session" ,
        bodyText = "Are you sure, you want to delete this session? your stuied hours will be " + "reduced by this session time action cant be undo " ,
        onDissmissRequest = { isdeletesessionOpen = false } ,
        onConformation = { isdeletesessionOpen = false })
    Scaffold(topBar = { DashBoradScrrentopbar() }) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()

        ) {
            item {
                countCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp , bottom = 8.dp , end = 8.dp
                        ) , 12 , "hi" , "hi"
                )
                // subjectCardSection()
            }
            item {
                subjectCardSection(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp) ,
                    subjects ,
                    isAddSubjectDialog = { isAddSubjectDialogOpen = true })
            }
            item {
                Button(onClick = {} , modifier = Modifier
                    .fillMaxWidth()
                    .padding(48.dp , 15.dp)) {
                    Text(text = "Start Study Session")
                }
            }
            taskList(sectionTitle = "UPCOMING TASK" ,
                empltyString = "YOU dont have any upcoming task. \n " + "Click the + button in screen to add new task " ,
                task = tasks ,
                onTaskClick = {} ,
                onTaskCardClcik = {})
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            StudySeccionsdependencies {
                classpath("com.android.tools.build:gradle:8.0.0") // Change to a compatible version
            }List(sectionTitle = "RECENT STUDY SESSION" ,
                empltyString = "YOU dont have any recent session. \n " + "Start a study session to begin recording your progress " ,
                session = session ,
                onClickIconDelete = { isdeletesessionOpen = true })
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashBoradScrrentopbar() {
    CenterAlignedTopAppBar(title = {
        Text(
            "StudySmart" , style = MaterialTheme.typography.headlineMedium
        )
    })
}

@Composable
private fun countCardSection(
    modifier : Modifier , subjectCount : Int , studyHour : String , goalHour : String
) {
    Row(modifier = modifier.padding(vertical = 0.dp)) {
        Countcard(modifier = Modifier.weight(1f) , "Subject Count" , "$subjectCount")
        Spacer(modifier = Modifier.width(8.dp))
        Countcard(modifier = Modifier.weight(1f) , "Study Hour" , studyHour)
        Spacer(modifier = Modifier.width(8.dp))
        Countcard(modifier = Modifier.weight(1f) , "Goal Hour" , goalHour)
    }
}

@Composable
private fun subjectCardSection(
    modifier : Modifier ,
    subjectList : List<Subject> ,
    empltyString : String = "You Don't have any Subject. \n Click the  + Button to add New Subject." ,
    isAddSubjectDialog : () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp) ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "SUBJECT" ,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 15.sp , fontWeight = FontWeight.SemiBold
                ) ,
                // modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = isAddSubjectDialog) {
                Icon(imageVector = Icons.Default.Add , contentDescription = "Add Subject")
            }
        }
        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally) ,
                painter = painterResource(R.drawable.img_books) ,
                contentDescription = ""
            )
            Text(
                empltyString ,
                style = MaterialTheme.typography.bodySmall ,
                modifier = Modifier.fillMaxWidth() ,
                color = Color.Gray ,
                textAlign = TextAlign.Center
            )

        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp) ,
            contentPadding = PaddingValues(start = 8.dp , end = 8.dp)
        ) {
            items(subjectList) { subject ->
                SubjectCard(subjectname = subject.name ,
                    gradientColor = subject.Color ,
                    onClick = {})
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun show() {
    DashboradScrren()

}
