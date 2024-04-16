// HomePage.kt

package com.example.petcareapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.example.petcareapp.data.TaskData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(tasks: List<TaskData>) {

    var showCreateTask by remember { mutableStateOf(false) }
    if (showCreateTask) {
        CreateTaskPage(onBack = { showCreateTask = false })
        return
    }

    val tasks = remember {
        listOf(
            TaskData(
                taskName = "Walk Johnny",
                petName = "Pet 1",
                assignee = "John Doe",
                dueDate = "May 15, 2024",
                dueTime = "10:00 AM"
            ),
            TaskData(
                taskName = "Feed Fido",
                petName = "Pet 2",
                assignee = "Jane Smith",
                dueDate = "May 16, 2024",
                dueTime = "12:00 PM"
            ),
            TaskData(
                taskName = "Clean litter box",
                petName = "Pet 3",
                assignee = "Alex Johnson",
                dueDate = "May 17, 2024",
                dueTime = "3:00 PM"
            )
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Today's Tasks",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tasks) { task ->
                Task(
                    taskName = task.taskName,
                    petName = task.petName,
                    assignee = task.assignee,
                    dueDate = task.dueDate,
                    dueTime = task.dueTime
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    thickness = 0.2.dp
                )
            }
        }

        Button(
            onClick = {
                showCreateTask = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create Task +")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(tasks = TaskData.sampleTasks)
}
