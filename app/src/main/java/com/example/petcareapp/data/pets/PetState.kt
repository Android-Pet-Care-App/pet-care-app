package com.example.petcareapp.data.pets

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf

data class PetState(
    val pets: List<Pet> = emptyList(),
    val isStoredByDateAdded: MutableState<Boolean> = mutableStateOf(true),

    val petName: MutableState<String> = mutableStateOf(""),
    val animal: MutableState<String> = mutableStateOf(""),
    val breed: MutableState<String> = mutableStateOf(""),
    val petAge: MutableState<Long> = mutableLongStateOf(0),
    val dateAdded: MutableState<Long> = mutableLongStateOf(0),
)
