package com.example.petcareapp.screens.landing

import androidx.compose.runtime.mutableStateOf
import com.example.petcareapp.HOME_SCREEN
import com.example.petcareapp.LANDING_SCREEN
import com.example.petcareapp.LOGIN_SCREEN
import com.example.petcareapp.SIGN_UP_SCREEN
import com.example.petcareapp.model.service.AccountService
import com.example.petcareapp.model.service.ConfigurationService
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService
) : PetCareAppViewModel(logService) {
    val showError = mutableStateOf(false)
    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if (accountService.hasUser) openAndPopUp(HOME_SCREEN, LANDING_SCREEN)
    }
}
