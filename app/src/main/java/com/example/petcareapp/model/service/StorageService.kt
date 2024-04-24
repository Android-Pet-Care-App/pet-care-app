package com.example.petcareapp.model.service

import com.example.petcareapp.model.Pet
import com.example.petcareapp.model.Task
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val tasks: Flow<List<Task>>
    val pets: Flow<List<Pet>>
    suspend fun getTask(taskId: String): Task?
    suspend fun getPet(petId: String): Pet?
    suspend fun save(task: Task): String
    suspend fun save(pet: Pet): String
    suspend fun update(task: Task)
    suspend fun update(pet: Pet)
    suspend fun deleteTask(taskId: String)
    suspend fun deletePet(petId: String)

}