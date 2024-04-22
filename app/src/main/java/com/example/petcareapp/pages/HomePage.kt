// HomePage.kt

package com.example.petcareapp.pages

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.example.petcareapp.CreateTaskPage
import com.example.petcareapp.TaskBar
import com.example.petcareapp.data.tasks.TaskEvent
import com.example.petcareapp.data.tasks.TaskState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(
   taskState: TaskState,
   onTaskEvent: (TaskEvent) -> Unit
) {
    var showCreateTask by remember { mutableStateOf(false) }
    if (showCreateTask) {
        CreateTaskPage(taskState,onTaskEvent,onBack = { showCreateTask = false })
        return
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
            items(taskState.tasks) { task ->
                TaskBar(task,onTaskEvent)
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
    //HomePage()
}
