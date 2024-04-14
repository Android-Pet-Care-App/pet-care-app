// Task.kt

package com.example.petcareapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task(
    taskName: String,
    petName: String,
    assignee: String,
    dueDate: String? = null,
    dueTime: String
) {
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
                        Text(text = dueDate, fontSize = 10.sp)
                    }
                }
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(text = "Due Time:", fontSize = 10.sp)
                    Text(text = dueTime, fontSize = 10.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    Task(
        taskName = "Walk Johnny",
        petName = "Pet 1",
        assignee = "John Doe",
        dueDate = "May 15, 2024",
        dueTime = "10:00 AM"
    )
}
