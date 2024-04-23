package com.example.petcareapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.petcareapp.CreateAddPetPage
import com.example.petcareapp.SubmitButton
import com.example.petcareapp.data.pets.Pet
import com.example.petcareapp.data.pets.PetEvent
import com.example.petcareapp.data.pets.PetState
import com.example.petcareapp.getAgeStringFromUnixTime
import com.example.petcareapp.getDateFromUnixTime

@Composable
fun PetsPage(
    petState: PetState,
    onPetEvent: (PetEvent) -> Unit
) {

    var showAddPet by remember { mutableStateOf(false) }
    if(showAddPet){
        CreateAddPetPage(petState,onPetEvent,onBack = { showAddPet = false })
        return
    }

    Text(
        text = "My Pets",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 16.dp, start = 15.dp)
    )
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            items(petState.pets) { pet ->
                PetItem(pet)
            }
        }

        SubmitButton(title = "Add Pet") { showAddPet = true }
    }
}


@Composable
fun PetItem(pet: Pet) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.LightGray, shape = CircleShape) // Use CircleShape here
        ) {
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
    Text( text = pet.petName)
    Text( text = pet.animal)
    Text( text = pet.breed)
    Text( text = getAgeStringFromUnixTime(pet.petAge))
    Text( text = getDateFromUnixTime(pet.dateAdded))
}

@Composable
fun ThreeDots() {
    Text(
        text = "...",
        color = Color.Blue,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .padding(start = 4.dp)
            .clickable { /* Handle dot click action */ }
    )
}



