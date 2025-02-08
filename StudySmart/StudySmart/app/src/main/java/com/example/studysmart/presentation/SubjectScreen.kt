package com.example.studysmart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import com.example.studysmart.presentation.components.AddSubjectDialog
import com.example.studysmart.presentation.components.Countcard
import com.example.studysmart.presentation.components.DeleteDialog
import com.example.studysmart.presentation.components.StudySeccionsList
import com.example.studysmart.presentation.components.taskList
import com.example.studysmart.session
import com.example.studysmart.tasks

data class SubjectScreenNavArg(
    val subjectId:Int
)

@Composable
//@Destination(navArgsDelegate = SubjectScreenNavArg::class)
fun SubjectScreenRoute()
{
    SubjectScreen()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreen()
{   val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val isFABExpand by remember {
       derivedStateOf {
           listState.firstVisibleItemIndex == 0 }}


    var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHour by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectColor.random()) }
    var isdeletesessionOpen by rememberSaveable { mutableStateOf(false) }
    var isdeleteSubjectOpen by rememberSaveable { mutableStateOf(false) }


    AddSubjectDialog(
        isOpen = isEditSubjectDialogOpen,
        onConformation = {isEditSubjectDialogOpen=false},
        onDissmissRequest = {isEditSubjectDialogOpen=false},
        selectedColor = selectedColor,
        onColorChnage = {selectedColor=it},
        subjectName = subjectName,
        goalHour = goalHour,
        onSubjectNameChnage = {subjectName=it},
        onGoalNameChnage = {goalHour=it}

    )
    DeleteDialog(
        isOpen = isdeletesessionOpen,
        title = "Delete Session",
        bodyText = "Are you sure, you want to delete this session? your stuied hours will be " +
                "reduced by this session time action cant be undo ",
        onDissmissRequest = {isdeletesessionOpen=false},
        onConformation = {isdeletesessionOpen=false}
    )
    DeleteDialog(
        isOpen = isdeleteSubjectOpen,
        title = "Delete Subject",
        bodyText = "Are you sure, you want to delete this subject? All related " +
                "task and study session will be permanently removed. This action cant be undone",
        onDissmissRequest = {isdeleteSubjectOpen=false},
        onConformation = {isdeleteSubjectOpen=false}
    )
    Scaffold (
       modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection ),
        topBar = {
            SubjectScreenTopBar("English",
            onDeleteButtionClick = {isdeleteSubjectOpen=true},
            onBackButtionClick = {},
            onEditButtionClick = {isEditSubjectDialogOpen=true},
            scrollBehavior = scrollBehavior)
                 },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {},
                icon = { Icon( Icons.Default.Add,
                    contentDescription ="")},
                text = { Text("Add Task") }
            , expanded = isFABExpand)

        }
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            state = listState
        ) {
            item {
                SubjectOverView(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    studiedHours = "10",
                    goalHours = "15",
                    Progress = 0.23f
                )
            }
            taskList(
                sectionTitle="UPCOMING TASK",
                empltyString="YOU dont have any upcoming task. \n " +
                        "Click the + button in screen to add new task ",
                task = tasks,
                onTaskClick = {},
                onTaskCardClcik = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle="COMPLETED TASKS",
                empltyString="YOU dont have any completed task. \n " +
                        "Click the check box on completed task ",
                task = tasks,
                onTaskClick = {},
                onTaskCardClcik = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            StudySeccionsList(
                sectionTitle = "RECENT STUDY SESSION",
                empltyString = "YOU dont have any recent session. \n " +
                        "Start a study session to begin recording your progress ",
                session = session,
                onClickIconDelete = {isdeletesessionOpen=true}
            )


        }

    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title:String,
    onBackButtionClick: () -> Unit,
    onDeleteButtionClick: () -> Unit,
    onEditButtionClick: () -> Unit,
    scrollBehavior : TopAppBarScrollBehavior
){
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = title ,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
                )
        },
        navigationIcon = {
            IconButton(
                onClick = {onBackButtionClick()}
            ) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack
                , contentDescription = "") }
        },
        actions = {
            IconButton(onClick = {onDeleteButtionClick()}) {
                Icon(imageVector = Icons.Default.Delete
                    , contentDescription = "")
            }
            IconButton(onClick = onEditButtionClick) {
                Icon(imageVector = Icons.Default.Edit
                    , contentDescription = "")
            }
        }

    )
}
@Composable
private fun SubjectOverView(
    modifier : Modifier,
    studiedHours:String,
    goalHours:String,
    Progress:Float
){
    val preprogrss = remember ( Progress ){
        (Progress*100).toInt().coerceIn(0,100)
    }
    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier =  modifier
    ) {
        Countcard(
            headingText = "Gaol Study Hours" ,
            Count = goalHours ,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Countcard(
            headingText = "Gaol Study Hours" ,
            Count = studiedHours ,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier.size(75.dp) ,
            contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator(
                progress = { 1f } ,
                modifier = Modifier.fillMaxSize() ,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor ,
                strokeCap = StrokeCap.Round ,
                strokeWidth = 4.dp ,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            CircularProgressIndicator(
                progress = { Progress } ,
                modifier = Modifier.fillMaxSize() ,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor ,
                strokeCap = StrokeCap.Round ,
                strokeWidth = 4.dp ,
            )
            Text("${preprogrss}%")
        }
    }

}
