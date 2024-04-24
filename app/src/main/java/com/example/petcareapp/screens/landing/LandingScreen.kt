package com.example.petcareapp.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.petcareapp.common.composable.BasicButton
import com.example.petcareapp.common.composable.BasicTextButton
import com.example.petcareapp.R
import com.example.petcareapp.common.ext.basicButton
import com.example.petcareapp.common.ext.spacer
import com.example.petcareapp.ui.theme.PetCareAppTheme
import kotlinx.coroutines.delay
import com.example.petcareapp.R.string as AppText

@Composable
fun LandingScreen (
    openAndPopUp: (String, String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: LandingScreenViewModel = hiltViewModel()
) {
    LandingScreenContent(
        onAppStart = { viewModel.onAppStart(openAndPopUp) },
        onLoginClick = { viewModel.onLoginClick(openScreen) },
        onSignUpClick = { viewModel.onSignUpClick(openScreen) },
    )
}

@Composable
fun LandingScreenContent (
    modifier: Modifier = Modifier,
    onAppStart: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
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
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(vertical = 16.dp) // Add padding to the top
                .size(180.dp)
        )

        Column {
            BasicButton(text = AppText.sign_in, Modifier.basicButton()) {
                onLoginClick()
            }
            BasicButton(text = AppText.create_account, Modifier.basicButton()) {
                onSignUpClick()
            }
        }

        LaunchedEffect(true) {
            onAppStart()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    PetCareAppTheme {
        LandingScreenContent(
            onAppStart = { },
            onLoginClick = { },
            onSignUpClick = {}
        )
    }
}
