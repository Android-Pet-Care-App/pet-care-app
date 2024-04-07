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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun PetsPage() {
    var showAddPet by remember { mutableStateOf(false) } // Start with showing the pets list

    if (showAddPet) {
        // Show the page for adding a new pet and provide a way to go back
        CreateAddPetPage(onBack = { showAddPet = false })
    } else {
        // Show the pets listing with the Add Pet button
        Column {
            Greeting("Pets Page")
            Button(onClick = { showAddPet = true }) {
                Text(text = "Add Pet +")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAddPetPage(onBack: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        TopAppBar(
            title = { Text("Add Pet") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back")
                }
            }
        )


        var petName by remember { mutableStateOf("") }
        HeadingTextForPetTask("Pet Name")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = petName,
            onValueChange = { petName = it },
            placeholder = { Text(text = "e.g. Yogi") },
        )


        var petType by remember { mutableStateOf(false) }
        val options = listOf("No Selection", "Dog", "Cat", "Fish")
        var selectedIndex by remember { mutableIntStateOf(0) }
        HeadingTextForPetTask("Select Pet Type")
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
                    .clickable(onClick = { petType = true }),
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
                expanded = petType,
                onDismissRequest = { petType = false },
                modifier = Modifier.fillMaxWidth(),
                content = {
                    options.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedIndex = index
                                petType = false
                            }
                        )
                    }
                }
            )
        }

        HeadingTextForPetTask("Select Task")
        SelectPetMenuComponent()

        SubmitPetComponent(petName, selectedIndex, onBack)

    }
}

@Composable
fun HeadingTextForPetTask(heading: String) {
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        text = heading,
        style = MaterialTheme.typography.bodyLarge
    )

}

@Composable
fun SelectPetMenuComponent() {
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
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option ) Color.Blue else Color.LightGray
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

        HeadingTextForPetTask("Custom Task")
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Transparent)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            BasicTextField(
                value = customTask,
                onValueChange = {
                    customTask = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun SubmitPetComponent(petName: String, selectedIndex: Int, onBack: () -> Unit) {
    val context = LocalContext.current

    Button(
        onClick = {
            if (petName.isNotEmpty() && selectedIndex != 0) {
                Toast.makeText(context, "Pet was submitted", Toast.LENGTH_SHORT).show()

                onBack()
            } else {
                if (petName.isEmpty()) {
                    Toast.makeText(context, "Please enter a pet name", Toast.LENGTH_SHORT).show()
                }
                if (selectedIndex == 0) {
                    Toast.makeText(context, "Please select a pet type", Toast.LENGTH_SHORT).show()
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Add Pet")
    }
}



