package com.example.petcareapp.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Pet (
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val name: String = "",
    val animalType: String = "",
    val breed: String = "",
    val age: Int = 0,
    val userId: String = ""
)