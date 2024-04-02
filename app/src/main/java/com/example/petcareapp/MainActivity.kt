package com.example.petcareapp

import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petcareapp.ui.theme.PetCareAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetCareAppTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var selectedItem by remember { mutableIntStateOf(0) }
    Scaffold(
        //bottomBar = { BottomNavigationBar(selectedItem,onItem) }
        bottomBar = { BottomNavigationBar(selectedItem) { index -> selectedItem = index } }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding),selectedItem)
    }
}

@Composable
fun ProfilePage(){
    Column {
        Greeting("Profile Page")
        Greeting("BOB")
    }
}

@Composable
fun HomePage(){
    Column {
        Greeting("Home Page")
        Greeting("Jones")
    }
}

@Composable
fun LabelPage(){
    Column {
        Greeting("Label Page")
        Greeting("Couldn't find Paw Icon, So just put a random ICON")
    }
}


@Composable
fun BodyContent(modifier: Modifier = Modifier, pageIndex: Int) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when(pageIndex){
            0 -> ProfilePage()
            1 -> HomePage()
            2 -> LabelPage()
            else -> {
                Greeting("Error. Shouldn't Show this")
            }
        }
    }
}

@Composable
fun BottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
//fun BottomNavigationBar(selectedItem: Int) {

    val items = listOf("Profile","Home", "Label")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (index==0) Icons.Filled.Person
                        else if (index==1) Icons.Filled.Home
                        else Icons.Filled.Face,
                        contentDescription = item
                    )},
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                    // selectedItem = index
                    // Add your action here when a navigation item is selected
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier.padding(24.dp), color = Color.Black)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PetCareAppTheme {
        AppContent()
    }
}
