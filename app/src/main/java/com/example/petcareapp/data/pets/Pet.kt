package com.example.petcareapp.data.pets

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pet(
    val petName: String,
    val animal: String,
    val breed: String,
    val petAge: Long,
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
