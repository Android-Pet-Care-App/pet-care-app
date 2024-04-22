package com.example.petcareapp.data.pets

sealed interface PetEvent {
    object SortTasks: PetEvent
    data class SavePet(val pet: Pet): PetEvent
    data class DeletePet(val pet: Pet): PetEvent
}
