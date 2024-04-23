package com.example.petcareapp.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.makeitso.common.composable.BasicButton
//import com.example.makeitso.common.composable.BasicTextButton
import com.example.petcareapp.R
import com.example.petcareapp.common.ext.basicButton

@Composable
fun LandingScreen (
    goSignInPage:()-> Unit,
    goCreateAccountPage:()-> Unit
) {
    Column(
        modifier = Modifier
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
            BasicButton(text = "Sign In", Modifier.basicButton()) {
                goSignInPage()
            }
            BasicButton(text = "Create Account", Modifier.basicButton()) {
                goCreateAccountPage()
            }
        }

    }
}

@Composable
fun BasicButton(text: String, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}
