package com.example.petcareapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.petcareapp.ui.theme.PetCareAppTheme

@Composable
fun HomeScreen(
//    openAndPopUp: (String, String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent (
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Home Screen")
    }
}

@Preview(showBackground = true)
@Composable
private fun LandingScreenPreview() {
    PetCareAppTheme {
        HomeScreenContent(
        )
    }
}
