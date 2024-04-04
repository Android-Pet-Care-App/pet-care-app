package com.example.petcareapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun HomePage(){
    var showCreateTask by remember { mutableStateOf(false) }
    if (showCreateTask) {
        CreateTaskPage(onBack = { showCreateTask = false })
        return
    }

    Column {
        Greeting("Home Page")
        Greeting("Jones")
        Button(onClick = {showCreateTask = true}) {
            Text(text = "Create Task +")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskPage(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Create Task") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back")
                }
            }
        )
        // Content for creating a task
        Greeting("Task Details")
        // Add more content as needed
    }
}

