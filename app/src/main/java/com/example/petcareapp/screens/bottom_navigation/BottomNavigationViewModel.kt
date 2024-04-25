package com.example.petcareapp.screens.bottom_navigation

import androidx.compose.runtime.mutableStateOf
import com.example.petcareapp.HOME_SCREEN
import com.example.petcareapp.PET_SCREEN
import com.example.petcareapp.PROFILE_SCREEN
import com.example.petcareapp.model.service.AccountService
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.model.service.StorageService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    logService: LogService,
    accountService: AccountService
) : PetCareAppViewModel(logService) {
    val uiState = mutableStateOf(BottomNavigationUiState())

    fun toHome(openScreen: (String) -> Unit) {
        val navButtons = uiState.value.navButtons.toMutableMap()  // Convert to mutable map
        for ((key, _) in navButtons) {
            navButtons[key] = key == "home"
        }
        uiState.value = uiState.value.copy(navButtons = navButtons)
        launchCatching {
            openScreen(HOME_SCREEN)
        }
    }
    fun toPets(openScreen: (String) -> Unit) {
        val navButtons = uiState.value.navButtons.toMutableMap()  // Convert to mutable map
        for ((key, _) in navButtons) {
            navButtons[key] = key == "pets"
        }
        uiState.value = uiState.value.copy(navButtons = navButtons)
        launchCatching {
            openScreen(PET_SCREEN)
        }
    }

    fun toProfile(openScreen: (String) -> Unit) {
        val navButtons = uiState.value.navButtons.toMutableMap()  // Convert to mutable map
        for ((key, _) in navButtons) {
            navButtons[key] = key == "pets"
        }
        uiState.value = uiState.value.copy(navButtons = navButtons)
        launchCatching {
            openScreen(PROFILE_SCREEN)
        }
    }
}
