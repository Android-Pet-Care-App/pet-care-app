package com.example.petcareapp.screens.pets

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.petcareapp.R
import com.example.petcareapp.common.composable.BasicButton
import com.example.petcareapp.common.ext.basicButton
import com.example.petcareapp.common.ext.spacer
import com.example.petcareapp.model.Pet
import com.example.petcareapp.ui.theme.PetCareAppTheme
import java.util.Date
import com.example.petcareapp.R.drawable as AppIcon
import com.example.petcareapp.R.string as AppText

@Composable
@ExperimentalMaterialApi
fun PetScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: PetsScreenViewModel = hiltViewModel(),
) {
    val pets = viewModel.pets.collectAsStateWithLifecycle(emptyList())
    val uiState by viewModel.uiState
    PetScreenContent(
        pets = pets.value,
        onPetSelect = viewModel::onPetSelect,
        onPetDelete = viewModel::onPetDelete,
        onAddPet = { viewModel.onAddPet(openAndPopUp) },
        uiState = uiState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun PetScreenContent (
    modifier: Modifier = Modifier,
    onPetSelect: (pet: Pet) -> Unit,
    uiState: PetScreenUiState,
    onPetDelete: (pet: Pet) -> Unit,
    onAddPet: () -> Unit,
    pets: List<Pet>
) {
    val (showCard, setShowCard) = remember { mutableStateOf(false) }
    val (showPets, setShowPets) = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Pets",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (pets.isNotEmpty()) {
            Column {
                pets.forEach { petItem ->
                    PetCard(
                        name = petItem.name,
                        breed = petItem.breed,
                        animalType = petItem.animalType,
                    ) {
                        onPetSelect(petItem)
                        setShowCard(true)
                    }
                }
            }
        } else {
            Text(text = "There are currently no pets")
        }
        Spacer(modifier = Modifier.weight(1f))

        BasicButton(AppText.create_pet, Modifier.basicButton()) { onAddPet() }
    }
    if (showCard) {
        PetCardPopup(
            pet = uiState.selectedPet,
            onPetDelete = onPetDelete,
            onClose = { setShowCard(false) }
        )
    }
}


@Composable
fun PetCardPopup(
    pet: Pet,
    onPetDelete: (pet: Pet) -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp) // Set maximum width for the card
                .padding(16.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Close button
                    Text(
                        text = "Close",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable(onClick = onClose),
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Animal Type: " + pet.animalType,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Breed: " + pet.breed,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Age: " + pet.age.toString(),
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
                Button(onClick = { onPetDelete(pet) }) {
                    Text(text = "Delete Pet")
                }
            }
        }
    }
}
@Composable
fun PetCard(
    name: String,
    breed: String,
    animalType: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick), // Make the card clickable
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display the icon on the left
            val icon: Painter
            if ( animalType == "Dog") {
                icon = painterResource(id = R.drawable.dog)
            } else if ( animalType == "Cat") {
                icon = painterResource(id = R.drawable.cat)
            } else {
                icon = painterResource(id = R.drawable.paw)
            }

            Image(
                painter = icon,
                contentDescription = "Icon",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(28.dp)
            )

            // Display pet name and assignee in a Column with weight
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = breed,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}
