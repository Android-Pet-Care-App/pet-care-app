package com.example.petcareapp.pages.other

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petcareapp.DatePickerButton
import com.example.petcareapp.DropDownSelect
import com.example.petcareapp.FormTextInput
import com.example.petcareapp.HeadingTextForFormData
import com.example.petcareapp.SubmitButton
import com.example.petcareapp.TimePickerButton
import com.example.petcareapp.convertToUnixTime
import com.example.petcareapp.data.tasks.TaskEvent
import com.example.petcareapp.data.tasks.Task
import com.example.petcareapp.data.tasks.TaskState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskPage(
    taskState: TaskState,
    onTaskEvent: (TaskEvent) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val selectedDate = remember { mutableStateOf("") }
    val selectedTime = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        TopAppBar(
            title = { Text("Create Task") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back")
                }
            }
        )

        FormTextInput("Task Name","e.g. Walk Johnny the Pet",taskState.taskName.value) { newTskName -> taskState.taskName.value = newTskName }

        val options = listOf("Pet 1", "Pet 2", "Pet 3","bob")
        DropDownSelect("Select Pet",options,taskState.petName.value) { newOpt -> taskState.petName.value = newOpt }
        HeadingTextForFormData(heading = "Due")
        Row {
//            DatePickerButton(selectedDate)
//            TimePickerButton(selectedTime)
        }

        SubmitButton("Create Task") {
            if(taskState.taskName.value.isBlank()){
                Toast.makeText(context,"Task Name is empty",Toast.LENGTH_LONG).show()
                return@SubmitButton
            }
            if(taskState.petName.value == ""){
                Toast.makeText(context,"No Pet Selected",Toast.LENGTH_LONG).show()
                return@SubmitButton
            }
            if(selectedDate.value.isBlank() || selectedTime.value.isBlank()){
                Toast.makeText(context,"Date and/or time not selected",Toast.LENGTH_LONG).show()
                return@SubmitButton
            }

            val task = Task(
                taskName = taskState.taskName.value,
                petName = taskState.petName.value,
                assignee = "Me",
                completed = false,
                dueDate = convertToUnixTime(selectedDate.value, selectedTime.value),
                dateAdded = System.currentTimeMillis(),
            )
            //Toast.makeText(context,task.toString(),Toast.LENGTH_LONG).show()
            onTaskEvent(TaskEvent.SaveTask(task))
            onBack()
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CreateTaskPagePreview() {
    val taskState = TaskState()
    CreateTaskPage(
        taskState = taskState,
        onTaskEvent = { /* Define action for task event */ },
        onBack = { /* Define action for back button */ }
    )
}


