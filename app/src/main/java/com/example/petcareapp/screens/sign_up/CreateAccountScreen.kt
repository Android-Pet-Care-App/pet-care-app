package com.example.petcareapp.screens.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.makeitso.common.composable.BasicButton
import com.example.makeitso.common.composable.BasicToolbar
import com.example.makeitso.screens.login.MyEmailField
import com.example.makeitso.screens.login.MyPasswordField
import com.example.petcareapp.R.string as AppText
import com.example.petcareapp.common.ext.basicButton
import com.example.petcareapp.common.ext.fieldModifier

@Composable
fun CreateAccountScreen(
    goLandingPage:()-> Unit,
    goSignInPage:()-> Unit,
    goHomePage:()-> Unit,
) {

    BasicToolbar(AppText.create_account)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = remember { mutableStateOf("") }
        MyEmailField(email.value, {onNewValue -> email.value=onNewValue}, Modifier.fieldModifier())

        val password = remember { mutableStateOf("") }
        MyPasswordField(password.value, {onNewValue -> password.value=onNewValue}, Modifier.fieldModifier())

        val repeatPass = remember { mutableStateOf("") }
        MyPasswordField(repeatPass.value, {onNewValue -> repeatPass.value=onNewValue})

        BasicButton(AppText.create_account, Modifier.basicButton()) {
            // validate logic
            goHomePage()
        }
    }
}