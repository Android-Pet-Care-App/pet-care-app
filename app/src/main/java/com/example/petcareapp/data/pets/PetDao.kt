package com.example.petcareapp.data.pets

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petcareapp.data.pets.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Insert
    suspend fun addPet(pet: Pet) // suspend waits to finish opperation and then moves on (sync)

    @Delete
    suspend fun delPet(pet: Pet)

    @Query("SELECT * from PET ORDER BY dateAdded")
    fun getPetsByDateAdded(): Flow<List<Pet>>

    @Query("SELECT * from PET ORDER BY petAge")
    fun getPetsByAge(): Flow<List<Pet>>
}
