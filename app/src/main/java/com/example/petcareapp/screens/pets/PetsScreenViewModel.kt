package com.example.petcareapp.screens.pets

import androidx.compose.runtime.mutableStateOf
import com.example.petcareapp.CREATE_PET_SCREEN
import com.example.petcareapp.PET_SCREEN
import com.example.petcareapp.model.Pet
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.model.service.StorageService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetsScreenViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : PetCareAppViewModel(logService) {
    val pets = storageService.pets
    val uiState = mutableStateOf(PetScreenUiState())
    fun onAddPet(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            openAndPopUp(CREATE_PET_SCREEN, PET_SCREEN)
        }
    }

    fun onPetSelect(pet: Pet) {
        uiState.value.selectedPet = pet
    }

    fun onPetDelete(pet: Pet) {
        launchCatching { storageService.deletePet(pet.id) }
    }
}
