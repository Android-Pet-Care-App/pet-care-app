package com.example.petcareapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage() {
    var showCreateTask by remember { mutableStateOf(false) }
    if (showCreateTask) {
        CreateTaskPage(onBack = { showCreateTask = false })
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Add margin around the Column
    ) {
        Text(
            text = "Today's Tasks",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { showCreateTask = true },
            modifier = Modifier
                .fillMaxWidth() // Button takes full width of parent
                .padding(horizontal = 20.dp) // Horizontal padding of 20.sp
                .align(Alignment.End) // Align button to the end (bottom) of the column
        ) {
            Text(text = "Create Task +")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomePagePreview() {
    HomePage() // Simply call your composable function within the preview function
}