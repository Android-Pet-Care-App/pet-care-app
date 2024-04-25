package com.example.petcareapp.screens.profile

import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@ExperimentalMaterialApi
fun ProfileScreen(
    openScreen: (String) -> Unit,
    viewModel: ProfileScreenViewModel = hiltViewModel(),
) {
    Button(onClick = { viewModel.onSignOut(openScreen) }) {
        Text(text = "Sign Out")
    }
}
