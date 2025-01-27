package com.example.studysmart.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studysmart.domain.model.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListBottomSheet(
    sheetState : SheetState,
    isopen:Boolean,
    subjects : List<Subject>,
    bottomSheetitiel:String="Related to subject",
    onSubjectClick :(Subject)->Unit,
    onDismissRequest:()->Unit
){
    if (isopen){

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() } ,
        sheetState = sheetState ,
        dragHandle = {
            Column(
                modifier = Modifier.fillMaxWidth() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDefaults.DragHandle()
                Text(text = bottomSheetitiel)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        )
        {
            items(subjects)
            {
                Box(modifier = Modifier.fillMaxWidth()
                    .clickable { onSubjectClick(it) }
                    .padding(vertical = 8.dp)
                ) {
                    Text(it.name)
                }
            }
            if (subjects.isEmpty()) {
                item {
                    Text(text = "Ready to begin? add a subject to get started")
                }
            }
        }
    }
}
}