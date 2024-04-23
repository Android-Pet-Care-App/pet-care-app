import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.petcareapp.CreateTaskPage
import com.example.petcareapp.data.TaskData

@Composable
fun HomePage(tasks: List<TaskData>) {
    var selectedTask by remember { mutableStateOf<TaskData?>(null) }
    var showCreateTask by remember { mutableStateOf(false) }

    if (showCreateTask) {
        CreateTaskPage(onBack = { showCreateTask = false })
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
            items(tasks) { task ->
                TaskItem(task = task) { selectedTask = task }
                Spacer(modifier = Modifier.height(8.dp))
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

//        selectedTask?.let { task ->
//            TaskDetailsDialog(task = task, onClose = { selectedTask = null })
//        }
    }
}

@Composable
fun TaskItem(task: TaskData, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = task.taskName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = task.petName,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Checkbox(
                checked = false, // change this on click
                onCheckedChange = null // change this on click
            )
        }
    }
}

//@Composable
//fun TaskDetailsDialog(task: TaskData, onClose: () -> Unit) {
//    Dialog(onCloseRequest = onClose) {
//        Surface(
//            shape = MaterialTheme.shapes.medium,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(
//                    text = task.taskName,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Text(
//                    text = "Due: ${task.dueTime}",
//                    fontSize = 16.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Text(
//                    text = "Assignee: ${task.assignee}",
//                    fontSize = 16.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.padding(bottom = 16.dp)
//                )
//                Row(
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    IconButton(onClick = onClose) {
//                        Icon(
//                            imageVector = M3Icons.Close,
//                            contentDescription = "Close"
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(tasks = TaskData.sampleTasks)
}
