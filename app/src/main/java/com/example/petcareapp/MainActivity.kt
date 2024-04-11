package com.example.petcareapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petcareapp.ui.theme.PetCareAppTheme

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
    var currPageInd by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = { BottomNavigationBar(currPageInd) { index -> currPageInd = index } }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding), currPageInd)
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, pageIndex: Int) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (pageIndex) {
            0 -> ProfilePage()
            1 -> HomePage()
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
