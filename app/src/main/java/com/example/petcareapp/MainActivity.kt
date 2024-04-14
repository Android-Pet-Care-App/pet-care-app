package com.example.petcareapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petcareapp.data.TaskData
import com.example.petcareapp.ui.theme.PetCareAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetCareAppTheme {
                val fakeTasks = listOf(
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

                AppContent(fakeTasks)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent(tasks: List<TaskData>) {
    var currPageInd by remember { mutableIntStateOf(1) }
    Scaffold(
        bottomBar = { BottomNavigationBar(currPageInd) { index -> currPageInd = index } }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding), currPageInd, tasks)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyContent(modifier: Modifier = Modifier, pageIndex: Int, tasks: List<TaskData>) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (pageIndex) {
            0 -> ProfilePage()
            1 -> HomePage(tasks = tasks)
            2 -> PetsPage(title = "My Pets")
        }
    }
}

@Composable
fun BottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Profile", "Home", "Pets")
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (index == 0) Icons.Filled.Person
                        else if (index == 1) Icons.Filled.Home
                        else Icons.Filled.ShoppingCart,
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier.padding(24.dp), color = Color.Black)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PetCareAppTheme {
        val fakeTasks = listOf(
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

        AppContent(fakeTasks)
    }
}
