package com.example.petcareapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun HomePage() {
    var showCreateTask by remember { mutableStateOf(!false) }
    if (showCreateTask) {
        CreateTaskPage(onBack = { showCreateTask = false })
        return
    }

    Column {
        Greeting("Home Page")
        Greeting("Jones")
        Button(onClick = { showCreateTask = true }) {
            Text(text = "Create Task +")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskPage(onBack: () -> Unit) {
    val context = LocalContext.current


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

        var taskName by remember { mutableStateOf("") }
        HeadingTextForCreateTask("Task Name")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = taskName,
            onValueChange = { taskName = it },
            placeholder = { Text(text = "e.g. Walk Dog") },
        )

        var expanded by remember { mutableStateOf(false) }
        val options = listOf("Pet 1", "Pet 2", "Pet 3")
        var selectedIndex by remember { mutableIntStateOf(0) }
        HeadingTextForCreateTask("Select Pet")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(34.dp)
                .background(Color("#dde3ea".toColorInt()))
                .wrapContentSize(Alignment.TopStart)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .clickable(onClick = { expanded = true }),
                horizontalArrangement = Arrangement.SpaceBetween, // This ensures space distribution between elements
                verticalAlignment = Alignment.CenterVertically // This centers the elements vertically in the Row
            ) {
                Text(
                    text = options[selectedIndex],
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "down arrow",
                    modifier = Modifier.size(24.dp) // Ensures the Icon has a specific size, making it more likely to be visible
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(),
                content = {
                    options.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedIndex = index
                                expanded = false
                            }
                        )
                    }
                }
            )


        }

        HeadingTextForCreateTask("Select Task")
        SelectMenuComponent()

    }
}

@Composable
fun HeadingTextForCreateTask(heading: String) {
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = heading,
        style = MaterialTheme.typography.bodyLarge
    )

}

@Composable
fun SelectMenuComponent() {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Walk", "Feed", "Change Litter")

    Column {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { option ->
                Button(
                    onClick = { selectedOption = option },
                    modifier = Modifier
                        .padding(1.dp),
                    // Change the background color if this option is selected
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) Color.Blue else Color.LightGray
                    )
                ) {
                    Text(
                        text = option,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }

        var customTask by remember { mutableStateOf("") }

        val context = LocalContext.current

        var inputColor = if (selectedOption == customTask) Color.Blue else Color.Transparent
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(inputColor)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            BasicTextField(
                value = customTask,
                onValueChange = {
                    customTask = it
                    selectedOption = customTask
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}