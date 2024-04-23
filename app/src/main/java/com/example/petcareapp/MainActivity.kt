package com.example.petcareapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.petcareapp.data.tasks.TaskDatabase
import com.example.petcareapp.data.tasks.TaskEvent
import com.example.petcareapp.data.tasks.TaskState
import com.example.petcareapp.data.tasks.TaskViewModel

import com.example.petcareapp.data.pets.PetDatabase
import com.example.petcareapp.data.pets.PetEvent
import com.example.petcareapp.data.pets.PetState
import com.example.petcareapp.data.pets.PetViewModel
import com.example.petcareapp.pages.HomePage
import com.example.petcareapp.pages.PetsPage
import com.example.petcareapp.ui.theme.PetCareAppTheme


class MainActivity : ComponentActivity() {
    private val taskDb by lazy { Room.databaseBuilder( applicationContext, TaskDatabase::class.java, "tasks.db" ).build() }
    private val taskViewModel by viewModels<TaskViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TaskViewModel(taskDb.taskDao) as T
            }
        }
    })

    private val petsDB by lazy { Room.databaseBuilder( applicationContext, PetDatabase::class.java, "pets.db" ).build() }
    private val petViewModel by viewModels<PetViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PetViewModel(petsDB.petDao) as T
                }
            }
        }
    )


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetCareAppTheme {
                val taskState by taskViewModel.state.collectAsState()
                val petState by petViewModel.state.collectAsState()
                AppContent(
                    taskState = taskState,
                    onTaskEvent = taskViewModel::onEvent,
                    petState = petState,
                    onPetEvent = petViewModel::onEvent,
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent(
    taskState: TaskState,
    onTaskEvent: (TaskEvent) -> Unit,
    petState: PetState,
    onPetEvent: (PetEvent) -> Unit
) {
    var currPageInd by remember { mutableIntStateOf(1) }
    Scaffold(
        bottomBar = { BottomNavigationBar(currPageInd) { index -> currPageInd = index } }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currPageInd) {
                0 -> ProfilePage()
                1 -> HomePage(taskState, onTaskEvent)
                2 -> PetsPage(petState,onPetEvent)
            }
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
        //AppContent()
    }
}
