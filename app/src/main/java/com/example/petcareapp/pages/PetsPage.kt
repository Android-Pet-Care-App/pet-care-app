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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp



@Composable
fun PetsPage(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(bottom = 16.dp, start = 15.dp)
    )
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // List of pets
        PetItem(name = "Fluffy")
        PetItem(name = "Spot")
        PetItem(name = "Whiskers")

        // Add Pet button
        Button(
            onClick = { },
            modifier = Modifier
                .padding(top = 16.dp)
                .height(48.dp)
                .padding(horizontal = 125.dp),
            elevation = ButtonDefaults.elevation( // Adjust elevation for 3D effect
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
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
            // You can replace this with actual pet image/icon
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.body1,
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
