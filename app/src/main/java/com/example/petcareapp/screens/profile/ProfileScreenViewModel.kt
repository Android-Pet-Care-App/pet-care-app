package com.example.petcareapp.screens.profile

import com.example.petcareapp.LANDING_SCREEN
import com.example.petcareapp.model.service.AccountService
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService
) : PetCareAppViewModel(logService) {
    fun onSignOut(openScreen: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            openScreen(LANDING_SCREEN)
        }
    }

}
