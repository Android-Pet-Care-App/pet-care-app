package com.example.petcareapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.foundation.clickable

@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Picture
        ProfilePicture(
            shape = CircleShape,
            backgroundColor = Color.Gray,
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        Text(
            text = "Your Name",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Groups Section
        Text(
            text = "Groups:",
            color = Color.Black,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Group Pictures Section
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) {
                GroupPicture(
                    shape = CircleShape,
                    backgroundColor = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Create Group Button
        CircularButton(
            onClick = { /* Handle click */ },
            modifier = Modifier.size(200.dp)
        ) {
            Text(
                text = "Create Group",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ProfilePicture(
    shape: Shape,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape)
    )
}

@Composable
fun GroupPicture(
    shape: Shape,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape)
    )
}

@Composable
fun CircularButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
