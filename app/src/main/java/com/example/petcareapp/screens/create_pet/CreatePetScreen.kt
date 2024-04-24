package com.example.petcareapp.screens.create_pet

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petcareapp.common.composable.ActionToolbar
import com.example.petcareapp.common.composable.BasicField
import com.example.petcareapp.R.drawable as AppIcon
import com.example.petcareapp.R.string as AppText
import com.example.petcareapp.common.composable.*
import com.example.petcareapp.common.ext.card
import com.example.petcareapp.common.ext.fieldModifier
import com.example.petcareapp.common.ext.spacer
import com.example.petcareapp.common.ext.toolbarActions
import com.example.petcareapp.model.Pet

@Composable
@ExperimentalMaterialApi
fun CreatePetScreen(
    popUpScreen: () -> Unit,
    viewModel: CreatePetScreenViewModel = hiltViewModel()
) {
    val pet by viewModel.pet
    val activity = LocalContext.current as AppCompatActivity

    CreatePetScreenContent(
        pet = pet,
        onDoneClick = { viewModel.onDoneClick(popUpScreen) },
        onNameChange = viewModel::onNameChange,
        onAnimalTypeChange = viewModel::onAnimalTypeChange,
        onBreedChange = viewModel::onBreedChange,
        onAgeChange = viewModel::onAgeChange,
        activity = activity
    )
}
@Composable
@ExperimentalMaterialApi
fun CreatePetScreenContent(
    modifier: Modifier = Modifier,
    pet: Pet,
    onDoneClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onAnimalTypeChange: (String) -> Unit,
    onBreedChange: (String) -> Unit,
    onAgeChange: (Int) -> Unit,
    activity: AppCompatActivity?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.create_pet,
            modifier = Modifier.toolbarActions(),
            primaryActionIcon = AppIcon.ic_check,
            primaryAction = { onDoneClick() }
        )
        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.pet_name, pet.name, onNameChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())

        CardSelectors(pet, onAnimalTypeChange, onBreedChange)

        NumberField(AppText.pet_age, pet.age, onAgeChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())
    }
}

@Composable
@ExperimentalMaterialApi
private fun CardSelectors(
    pet: Pet,
    onAnimalTypeChange: (String) -> Unit,
    onBreedChange: (String) -> Unit
) {
    val options1 = listOf("Type 1", "Type 2", "Type 3","Type 4")
    val animalTypeSelection = pet.animalType
    // TODO: replace options with animal types from api
    CardSelector(AppText.animal_type, options1, animalTypeSelection, Modifier.card()) {
            newValue ->
        onAnimalTypeChange(newValue)
    }
    val options2 = listOf("Breed 1", "Breed 2", "Breed 3","Breed 4")
    val breedSelection = pet.breed
    // TODO: replace options with breeds from api
    CardSelector(AppText.pet_breed, options2, breedSelection, Modifier.card()) {
            newValue ->
        onBreedChange(newValue)
    }
}