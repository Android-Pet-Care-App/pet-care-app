package com.example.petcareapp.screens.bottom_navigation

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petcareapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
@ExperimentalMaterialApi
fun BottomNavigation(
    openScreen: (String) -> Unit,
    viewModel: BottomNavigationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState

    BottomNavigationContent(
        toHome = { viewModel.toHome(openScreen) },
        toPets = { viewModel.toPets(openScreen) },
        toProfile = { viewModel.toProfile(openScreen) },
        uiState = uiState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun BottomNavigationContent (
    toHome: () -> Unit,
    toPets: () -> Unit,
    toProfile: () -> Unit,
    uiState: BottomNavigationUiState
) {
    NavigationBar {
        val petsIcon: Painter = painterResource(R.drawable.pets)
        uiState.navButtons["profile"]?.let { selected ->
            NavigationBarItem(
                selected = selected,
                label = {
                    Text(text = "Profile")
                },
                onClick = {
                    toProfile()
                    Log.d("TEST", uiState.navButtons.toString())
                },
                icon = {
                    androidx.compose.material3.Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                 }
            )
        }

        uiState.navButtons["home"]?.let { selected ->
            NavigationBarItem(selected = selected, label = {
                    Text(text = "Home")
                }, onClick = {
                    toHome()
                    Log.d("TEST", uiState.navButtons.toString())
                }, icon = {
                    androidx.compose.material3.Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                })
        }

        uiState.navButtons["pets"]?.let { selected ->
            NavigationBarItem(
                selected = selected,
                label = {
                    Text(text = "Pets")
                },
                onClick = { toPets() },
                icon = {
                    androidx.compose.material3.Icon(painter = petsIcon, contentDescription = "")
                }
            )
        }
    }

}

