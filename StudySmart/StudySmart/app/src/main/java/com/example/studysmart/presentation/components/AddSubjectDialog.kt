package com.example.studysmart.presentation.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studysmart.domain.model.Subject


@OptIn(ExperimentalMaterial3Api::class , ExperimentalLayoutApi::class)
@Composable
fun AddSubjectDialog(
    onDissmissRequest:()->Unit,
    onConformation:()->Unit,
    selectedColor:List<Color>,
    onColorChnage:(List<Color>)->Unit,
    subjectName:String,
    goalHour:String,
    onSubjectNameChnage:(String)->Unit,
    onGoalNameChnage:(String)->Unit,
    title:String="Add/Update Subject",
    isOpen:Boolean
) {
    var sujectnameError by rememberSaveable { mutableStateOf<String?>(null) }
    var goalHourError by rememberSaveable { mutableStateOf<String?>(null) }

    sujectnameError = when
    {
        subjectName.isBlank() -> "Please enter subject name."
        subjectName.length < 2 -> "Subject name is too short"
        subjectName.length> 15 -> "Subject name is too long. "
        else -> null
    }
    goalHourError = when
    {
        goalHourError?.isBlank()  == true -> "Please enter goal stydy hour"
        goalHour.toFloatOrNull() == null -> "Invalid number"
        goalHour.toFloat() <1 -> "please sett at least 1 hour"
        goalHour.toFloat()>1000 ->"please ser a maximum of 1000 hour"
    else ->null
    }

    if (isOpen)
        {
            AlertDialog(
                dismissButton = { TextButton(onClick = onDissmissRequest) { Text("cancel") } } ,
                title = { Text(text = title) } ,
                text = {
                    Column {
                        FlowRow (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .height(55.dp)
                                ,
                            horizontalArrangement = Arrangement.Absolute.Center
                            , maxItemsInEachRow = 6
                            , verticalArrangement = Arrangement.SpaceBetween
                        ){
                            Subject.subjectColor.forEach {
                                Row {
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Box(
                                        modifier = Modifier.size(24.dp)
                                            .clip(CircleShape)
                                            .border(width = 2.dp, color = if(it==selectedColor)
                                            {Color.Black}else Color.Transparent, shape = CircleShape)
                                            .background(brush = Brush.verticalGradient(it))
                                            .clickable { onColorChnage(it) }
                                    )
                                }
                            }

                        }
                        OutlinedTextField(
                            value = subjectName,
                            onValueChange = onSubjectNameChnage,
                            label = { Text("Subject Name") },
                            singleLine = true,
                            isError = sujectnameError !=null && sujectnameError!!.isNotBlank(),
                            supportingText = { Text(sujectnameError.orEmpty()) }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = goalHour,
                            onValueChange = onGoalNameChnage,
                            label = { Text("Goal Study Hours") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            isError = goalHourError !=null && goalHourError!!.isNotBlank(),
                            supportingText = { Text(goalHourError.orEmpty()) }
                        )
                    }
                     } ,
                confirmButton = {
                    TextButton(onClick = onConformation, enabled = sujectnameError==null &&
                            goalHourError ==null) {
                        Text("Save") } } ,
                onDismissRequest = onDissmissRequest
            )

        }

}



