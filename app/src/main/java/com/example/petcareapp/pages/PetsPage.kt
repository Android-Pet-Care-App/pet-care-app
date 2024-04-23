package com.example.petcareapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun PetsPage(title: String) {
    var showAddPet by remember { mutableStateOf(false) }
    if(showAddPet){
        CreateAddPetPage(onBack = { showAddPet = false })
        return
    }

    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 16.dp, start = 15.dp)
    )
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PetItem(name = "Fluffy")
        PetItem(name = "Spot")
        PetItem(name = "Whiskers")

        Button(
            onClick = { showAddPet = true },
            modifier = Modifier
                .padding(top = 16.dp)
                .height(48.dp)
                .padding(horizontal = 125.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            ),
            colors = ButtonDefaults.buttonColors(
                //containerColor = MaterialTheme.colorScheme.primaryContainer,
                containerColor = Color.Blue,
                contentColor = Color.White // Text color
            )
        ) {
            Text("Add Pet")
        }
    }
}


@Composable
fun PetItem(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, shape = CircleShape) // Use CircleShape here
        ) {
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        ThreeDots()
    }
}

@Composable
fun ThreeDots() {
    Text(
        text = "...",
        color = Color.Blue,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .padding(start = 4.dp)
            .clickable { /* Handle dot click action */ }
    )
}



