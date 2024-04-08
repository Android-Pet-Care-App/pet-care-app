package com.example.petcareapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage() {
    var showCreateTask by remember { mutableStateOf(false) }
    if (showCreateTask) {
        CreateTaskPage(onBack = { showCreateTask = false })
        return
    }

    Column {
        Greeting("Home Page")
        Button(onClick = { showCreateTask = true }) {
            Text(text = "Create Task +")
        }
    }
}