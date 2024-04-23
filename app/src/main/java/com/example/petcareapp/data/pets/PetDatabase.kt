package com.example.petcareapp.data.pets

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Pet::class],
    version = 1
)
abstract class PetDatabase: RoomDatabase() {
    abstract val petDao: PetDao
}
