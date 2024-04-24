package com.example.petcareapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import com.example.petcareapp.data.pets.Pet
import com.example.petcareapp.data.pets.PetEvent
import com.example.petcareapp.data.pets.PetState
import com.example.petcareapp.helpers.ApiService
import androidx.lifecycle.viewModelScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAddPetPage(
    petState: PetState,
    onPetEvent: (PetEvent) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val apiService = ApiService()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        TopAppBar(
            title = { Text("Add Pet") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.KeyboardArrowLeft, "Back")
                }
            }
        )


        FormTextInput(
            "Pet Name",
            "e.g. Yogi",
            petState.petName.value
        ) { newName -> petState.petName.value = newName }

        val animalOpts = listOf("Dog", "Cat", "Other")
        val otherAnimal = remember { mutableStateOf("") }
        val breedOpts = remember { mutableListOf<String>() }

        DropDownSelect( "Select Animal", animalOpts, petState.animal.value ) { newOpt -> petState.animal.value = newOpt }

        if (petState.animal.value == "Other") {
            FormTextInput( "Animal", "e.g. Cheetah", otherAnimal.value ) { newName -> otherAnimal.value = newName }
        } else {
            LaunchedEffect(petState.animal.value) {
                when (petState.animal.value) {
                    "Dog" -> fetchBreeds(apiService::fetchDogBreeds, breedOpts, context)
                    "Cat" -> fetchBreeds(apiService::fetchCatBreeds, breedOpts, context)
                }
            }

            DropDownSelect(
                "Select Breed",
                breedOpts,
                petState.breed.value
            ) { newOpt -> petState.breed.value = newOpt }
        }

        val selectedDate = remember { mutableStateOf("") }
        HeadingTextForFormData(heading = "Pet Birthday (optional)")
        Row {
            DatePickerButton(selectedDate)
            Button(onClick = { selectedDate.value = "" }) { Text(text = "Clear Date") }
        }

        SubmitButton(title = "Add Pet") {
            if (petState.petName.value.isBlank()) {
                Toast.makeText(context, "Pet Name is empty", Toast.LENGTH_LONG).show()
                return@SubmitButton
            }
            if (petState.animal.value.isBlank()) {
                Toast.makeText(context, "Animal is empty", Toast.LENGTH_LONG).show()
                return@SubmitButton
            }
            if (petState.breed.value.isBlank()) {
                Toast.makeText(context, "Breed is empty", Toast.LENGTH_LONG).show()
                return@SubmitButton
            }

            val thePet = Pet(
                petName = petState.petName.value,
                animal = petState.animal.value,
                breed = petState.breed.value,
                petAge = if (selectedDate.value == "") 0 else convertDateToUnixTime(selectedDate.value),
                dateAdded = System.currentTimeMillis(),
            )
            onPetEvent(PetEvent.SavePet(thePet))
            onBack()
            Toast.makeText(context, "Pet Added", Toast.LENGTH_LONG).show()
        }
    }
}

suspend fun fetchBreeds(
    fetchFunction: suspend ((List<String>) -> Unit, (Exception) -> Unit) -> Unit,
    breedOpts: MutableList<String>,
    context: Context
) {
    fetchFunction(
        { animalTypes ->  // onComplete
            breedOpts.clear()
            breedOpts.addAll(animalTypes)
        },
        { exception ->  // onError
            Toast.makeText(
                context,
                "Failed to fetch types: ${exception.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    )
}
