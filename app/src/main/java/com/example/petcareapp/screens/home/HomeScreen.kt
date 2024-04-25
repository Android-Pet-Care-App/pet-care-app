package com.example.petcareapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.petcareapp.R
import com.example.petcareapp.common.composable.BasicButton
import com.example.petcareapp.common.ext.basicButton
import com.example.petcareapp.common.ext.spacer
import com.example.petcareapp.model.Task
import com.example.petcareapp.ui.theme.PetCareAppTheme
import java.util.Date
import com.example.petcareapp.R.drawable as AppIcon
import com.example.petcareapp.R.string as AppText

@Composable
@ExperimentalMaterialApi
fun HomeScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
    val uiState by viewModel.uiState
    HomeScreenContent(
        tasks = tasks.value,
        onTaskSelect = viewModel::onTaskSelect,
        onTaskComplete = viewModel::onTaskComplete,
        onTaskDelete = viewModel::onTaskDelete,
        onAddTask = { viewModel.onAddTask(openAndPopUp) },
        uiState = uiState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun HomeScreenContent (
    modifier: Modifier = Modifier,
    onTaskSelect: (task: Task) -> Unit,
    uiState: HomeScreenUiState,
    onTaskComplete: (task: Task) -> Unit,
    onTaskDelete: (task: Task) -> Unit,
    onAddTask: () -> Unit,
    tasks: List<Task>
) {
    val (showCard, setShowCard) = remember { mutableStateOf(false) }
    val (showTasks, setShowTasks) = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Today's Tasks",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (tasks.isNotEmpty()) {
            Column {
                tasks.forEach { taskItem ->
                    TaskCard(
                        taskName = taskItem.taskName,
                        assignee = taskItem.assignee,
                        dueDate = taskItem.dueDate,
                        dueTime = taskItem.dueTime,
                        completed = taskItem.completed
                    ) {
                        onTaskSelect(taskItem)
                        setShowCard(true)
                    }
                }
            }
        } else {
            Text(text = "There are currently no tasks")
        }
        Spacer(modifier = Modifier.weight(1f))

        BasicButton(AppText.create_task, Modifier.basicButton()) { onAddTask() }
    }
    if (showCard) {
        TaskCardPopup(
            task = uiState.selectedTask,
            onTaskComplete = onTaskComplete,
            onTaskDelete = onTaskDelete,
            onClose = { setShowCard(false) }
        )
    }
}


@Composable
fun TaskCardPopup(
    task: Task,
    onTaskComplete: (task: Task) -> Unit,
    onTaskDelete: (task: Task) -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp) // Set maximum width for the card
                .padding(16.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Close button
                    Text(
                        text = "Close",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable(onClick = onClose),
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.taskName,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pet: " + task.petName,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Assignee: " + task.assignee,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Due Date: " + task.dueDate,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Due Time: " + task.dueTime,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Button(onClick = { onTaskDelete(task) }) {
                    Text(text = "Delete Task")
                }
                Button(onClick = { onTaskComplete(task) }) {
                    Text(text = "Complete Task")
                }
            }
        }
    }
}
@Composable
fun TaskCard(
    taskName: String,
    assignee: String,
    dueDate: String,
    completed: Boolean,
    dueTime: String,
    onClick: () -> Unit
) {
    val backgroundColor = if (completed) Color.Green else Color.White
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick), // Make the card clickable
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display the icon on the left
            val clockIcon: Painter = painterResource(id = R.drawable.paw)
            Image(
                painter = clockIcon,
                contentDescription = "Icon",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(28.dp)
            )

            // Display pet name and assignee in a Column with weight
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = taskName,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = assignee,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            // Display the due date aligned to the right
            val pawIcon: Painter = painterResource(id = R.drawable.clock)
            Image(
                painter = pawIcon,
                contentDescription = "Icon",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )
            Text(
                text = dueDate,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
    }
}