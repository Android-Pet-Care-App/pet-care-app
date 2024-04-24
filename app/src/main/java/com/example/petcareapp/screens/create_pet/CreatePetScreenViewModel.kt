package com.example.petcareapp.screens.create_pet

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.petcareapp.PET_ID
import com.example.petcareapp.common.ext.idFromParameter
import com.example.petcareapp.model.Pet
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.model.service.StorageService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePetScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val storageService: StorageService,
): PetCareAppViewModel(logService) {
    val pet = mutableStateOf(Pet())

    init {
        val petId = savedStateHandle.get<String>(PET_ID)
        Log.d("MyTag", petId ?: "No pet Id")
        if (petId != null) {
            launchCatching {
                pet.value = storageService.getPet(petId.idFromParameter()) ?: Pet()
            }
        }
    }

    fun onNameChange(newValue: String) {
        pet.value = pet.value.copy(name = newValue)
    }

    fun onAnimalTypeChange(newValue: String) {
        pet.value = pet.value.copy(animalType = newValue)
    }

    fun onBreedChange(newValue: String) {
        pet.value = pet.value.copy(breed = newValue)
    }

    fun onAgeChange(newValue: Int) {
        pet.value = pet.value.copy(age = newValue)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val createdPet = pet.value
            if (createdPet.id.isBlank()) {
                storageService.save(createdPet)
            } else {
                storageService.update(createdPet)
            }
            popUpScreen()
        }
    }
}