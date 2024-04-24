package com.example.petcareapp.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.petcareapp.model.Task
import com.example.petcareapp.ui.theme.PetCareAppTheme
import java.util.Date

@Composable
fun HomeScreen(
//    openAndPopUp: (String, String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
    HomeScreenContent(
        tasks = tasks.value
    )
}

@Composable
fun HomeScreenContent (
    modifier: Modifier = Modifier,
    tasks: List<Task>
) {
//    Log.d("TASK", tasks.toString())
    val (showCard, setShowCard) = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Today's Tasks",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { Log.d("GET TASKS", tasks.toString()) }) {
            Text(text = "Test")
        }
        Button(onClick = { setShowCard(true) }) {
            Text(text = "Show")
        }
    }
    if (showCard) {
        TaskCardPopup(
            onClose = { setShowCard(false) } // Pass callback to close the card
        )
    }
}


@Composable
fun TaskCardPopup(
    onClose: () -> Unit // Callback to close the card
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp) // Set maximum width for the card
                .padding(16.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Close button
                    Text(
                        text = "Close",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable(onClick = onClose),
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This is a sample card in Jetpack Compose.",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LandingScreenPreview() {
    val tasks = listOf(
        Task(
            "FDSJ(J*(#A",
            Date(),
            "Test Task",
            "pet name",
            "assignee",
            false,
            "",
            "",
            "",
        )
    )
    PetCareAppTheme {
        HomeScreenContent(
            tasks = tasks
        )
    }
}
