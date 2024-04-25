package com.example.petcareapp.screens.bottom_navigation

import com.example.petcareapp.HOME_SCREEN
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    logService: LogService
) : PetCareAppViewModel(logService) {
    fun toHome(openScreen: (String) -> Unit) {
        launchCatching {
            openScreen(HOME_SCREEN)
        }
    }
    fun toPets(openScreen: (String) -> Unit) {
        launchCatching {
        }
    }
}
