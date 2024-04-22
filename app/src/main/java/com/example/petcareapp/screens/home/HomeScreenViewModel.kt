package com.example.petcareapp.screens.home

import com.example.petcareapp.model.service.AccountService
import com.example.petcareapp.model.service.ConfigurationService
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService
) : PetCareAppViewModel(logService) {

}
