// Task.kt

package com.example.petcareapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petcareapp.data.tasks.Task

@Composable
fun TaskBar(task: Task) {
    val taskName: String = task.taskName
    val petName: String = task.petName
    val assignee: String = task.assignee
    val dueDate: Long = task.dueDate
    val dateAdded: Long = task.dateAdded

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
    ) {
        // Left side: Task details (taskName, petName, assignee)
        Column {
            Text(
                text = taskName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Pet: $petName", fontSize = 16.sp, modifier = Modifier.padding(bottom = 2.dp))
            Text(text = "Assignee: $assignee", fontSize = 16.sp, modifier = Modifier.padding(bottom = 16.dp))
        }
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (dueDate != null) {
                    Column(
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(text = "Due Date:", fontSize = 10.sp)
                        Text(text = dueDate.toString(), fontSize = 10.sp)
                    }
                }
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(text = "Due Time:", fontSize = 10.sp)
                    Text(text = "time", fontSize = 10.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    val t = Task(
        taskName = "Walk Johhny",
        petName = "Pet 2",
        assignee = "John Doe",
        completed = false,
        dueDate = 0,
        dateAdded = 0
    )
    TaskBar(t)
}
