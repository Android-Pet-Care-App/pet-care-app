package com.example.petcareapp.data.pets

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetViewModel(private val dao: PetDao): ViewModel() {
    private val isStoredByDateAdded: MutableStateFlow<Boolean> = MutableStateFlow(true)

    private val _pets = isStoredByDateAdded.flatMapLatest { sort ->
        if (sort){
            dao.getPetsByDateAdded()
        }else{
            dao.getPetsByAge()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(PetState())
    val state = combine(_state, isStoredByDateAdded,_pets) {state,isStoredByDateAdded, pets ->
            state.copy(
                isStoredByDateAdded = mutableStateOf(isStoredByDateAdded),
                pets=pets
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PetState())

    fun onEvent(event: PetEvent){
        when(event){
            PetEvent.SortTasks -> { isStoredByDateAdded.value = !isStoredByDateAdded.value }
            is PetEvent.DeletePet -> {
                viewModelScope.launch {
                    dao.delPet(event.pet)
                }
            }
            is PetEvent.SavePet -> {
                val pet = event.pet
                viewModelScope.launch {
                    dao.addPet(pet)
                }
                _state.update { it.copy(
                    petName = mutableStateOf(""),
                    animal = mutableStateOf(""),
                    breed = mutableStateOf(""),
                    petAge = mutableLongStateOf(0),
                    dateAdded = mutableLongStateOf(0),
                ) }
            }
            else -> {}
        }
    }
}
