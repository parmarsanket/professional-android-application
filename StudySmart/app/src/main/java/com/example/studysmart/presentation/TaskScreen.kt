package com.example.studysmart.presentation

import android.icu.text.SimpleDateFormat
import android.icu.util.LocaleData
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import com.example.studysmart.presentation.components.DeleteDialog
import com.example.studysmart.presentation.components.SubjectListBottomSheet
import com.example.studysmart.presentation.components.TaskCheckBox
import com.example.studysmart.presentation.components.TaskDatepicker
import com.example.studysmart.subjects
import com.example.studysmart.util.Priority

import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

data class TaskScreenNavArg(
    val taskId:Int?,
    val sunjectId:Int?
)
//@Destination(navArgsDelegate = TaskScreenNavArg::class)
@Composable
fun TaskScreenRoute()
{
    TastScreen()

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TastScreen()
{
    val scope = rememberCoroutineScope()
    var isdeleteDialogopen by rememberSaveable{ mutableStateOf(false) }
    var isDateDialogopen by rememberSaveable{ mutableStateOf(false) }
    val currentDate = LocalDate.now().plusDays(2)
    var datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        selectableDates =PastOrPresentSelectableDates
    )
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }

    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetopen by rememberSaveable { mutableStateOf(false) }

    val selectedDateMillis = datePickerState.selectedDateMillis

    val formattedDate = if (selectedDateMillis != null) {
        val date = Date(selectedDateMillis)
        val format = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        format.format(date)
    } else {
        "No date selected"
    }



    taskTitleError = when {
        title.isBlank() -> "Please enter task title."
        title.length<4 -> "Task title is too short"
        title.length>30 -> "Task title is too long"
        else -> null
    }
    DeleteDialog(
        isOpen = isdeleteDialogopen,
        title = "Delete",
        bodyText = "Are you sure, you want to delete this task?" +
                "this action cant be undone.",
        onConformation = {isdeleteDialogopen=false},
        onDissmissRequest = {isdeleteDialogopen=false}
    )
    TaskDatepicker(
        isopen = isDateDialogopen,
        state = datePickerState,
        onDissmissButtionClicked = {isdeleteDialogopen= false},
        onConfirmButtionClicked = {isDateDialogopen=false}
    )
    SubjectListBottomSheet(
        sheetState =sheetState ,
        isopen =  isBottomSheetopen,
        subjects = subjects,
        onSubjectClick ={
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) isBottomSheetopen=false
            }
        } ,
        onDismissRequest ={isBottomSheetopen=false} ,

    )
    Scaffold (
        topBar = { TaskScreenTopbar(
            isTaskExist = true,
            isComplete = false,
            onDeleteButtonClick = {isdeleteDialogopen=true},
            onBackButtonClick = {},
            onCheckBoxClick = {},
            checkBoxBorderColor = Color.Yellow
        ) }
    ){
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(it).padding(8.dp)
                .verticalScroll(state = rememberScrollState())
        ){
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {title=it},
                value = title,
                label = {Text("Title")} ,
                singleLine = true,
                isError = taskTitleError !=null && taskTitleError!!.isNotBlank(),
                supportingText = { Text( text = taskTitleError.orEmpty())}
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {description=it},
                value = description,
                label = {Text("Description")} ,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text("Due Date", style = MaterialTheme.typography.bodySmall)
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    formattedDate ,
                    style = MaterialTheme
                    .typography
                    .bodyLarge)
                IconButton(onClick = {isDateDialogopen=true}) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("  Priority", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                    Priority.entries.forEach{
                        PriorityButtio(
                            modifier = Modifier.weight(1f),
                            lable = it.title,
                            backgroundColor = it.Color,
                            borderColor = if(it==Priority.MEDIUM)
                            {
                                Color.White
                            }else Color.Transparent,
                            lableColor = if(it==Priority.MEDIUM)
                            {
                                Color.White
                            }else  Color.White.copy(alpha = 0.7f),
                            onClick = {}
                        )
                    }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text("Related to Subject", style = MaterialTheme.typography.bodySmall)
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("English", style = MaterialTheme.typography.bodyLarge)
                IconButton(onClick = {isBottomSheetopen=true}) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }

            }
            Button(
                enabled = taskTitleError==null,
                onClick ={},
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            {
                Text("Save")
            }


        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private  fun TaskScreenTopbar(
    isTaskExist : Boolean,
    isComplete:Boolean,
    checkBoxBorderColor:Color,
    onBackButtonClick:()->Unit,
    onDeleteButtonClick:()->Unit,
    onCheckBoxClick:()->Unit
){

    TopAppBar(
        navigationIcon ={ IconButton(onClick = {onBackButtonClick()})
        {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                contentDescription = "")
        }} ,
        title = { Text("Task", style = MaterialTheme.typography.headlineSmall) },
        actions = {
            if(isTaskExist){
                TaskCheckBox(
                    isComplate =isComplete,
                    borderColor = checkBoxBorderColor ,
                    onClickBoxClick = {onCheckBoxClick()}
                )
                IconButton(
                    onClick = {onDeleteButtonClick()}
                ) {  Icon(imageVector = Icons.Default.Delete ,
                    contentDescription = "")}
            }



        }

    )
}
@Composable
private fun PriorityButtio(
    modifier : Modifier = Modifier,
    lable:String,
    backgroundColor : Color,
    borderColor:Color,
    lableColor:Color,
    onClick:()->Unit
){
    Box( modifier=modifier
        .background(backgroundColor)
        .clickable { onClick() }
        .padding(5.dp)
        .border(1.dp,borderColor, RoundedCornerShape(5.dp))
        .padding(5.dp),
        contentAlignment = Alignment.Center) {
       Text(text = lable, color = lableColor)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
object PastOrPresentSelectableDates: SelectableDates {
    @ExperimentalMaterial3Api
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis > System.currentTimeMillis()
    }
    @ExperimentalMaterial3Api
    override fun isSelectableYear(year: Int): Boolean {
        return year >= LocalDate.now().year
    }
}

//fun Long?.changedateformat():String
//{
//    val date = this?.let {
//        Instant
//            .ofEpochMilli(it)
//            .atZone(ZoneId.systemDefault())
//            .toLocalDate()
//    }?:LocalDate.now()
//    return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
//}

