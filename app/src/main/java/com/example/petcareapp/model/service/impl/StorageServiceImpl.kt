package com.example.petcareapp.model.service.impl

import androidx.compose.ui.util.trace
import com.example.petcareapp.model.Pet
import com.example.petcareapp.model.Task
import com.example.petcareapp.model.service.AccountService
import com.example.petcareapp.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.flatMapLatest
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
): StorageService{
    private val collection get() = firestore.collection(TASK_COLLECTION)
        .whereEqualTo(USER_ID_FIELD, auth.currentUserId)
    override val tasks: Flow<List<Task>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore
                    .collection(TASK_COLLECTION)
                    .whereEqualTo(USER_ID_FIELD, user.id)
                    .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                    .dataObjects()
            }
    override val pets: Flow<List<Pet>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore
                    .collection(PET_COLLECTION)
                    .whereEqualTo(USER_ID_FIELD, user.id)
                    .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                    .dataObjects()
            }

    override suspend fun getTask(taskId: String): Task? =
        firestore.collection(TASK_COLLECTION).document(taskId).get().await().toObject()

    override suspend fun getPet(petId: String): Pet? =
        firestore.collection(PET_COLLECTION).document(petId).get().await().toObject()


    override suspend fun save(task: Task): String =
        trace(SAVE_TASK_TRACE) {
            val updatedTask = task.copy(userId = auth.currentUserId)
            firestore.collection(TASK_COLLECTION).add(updatedTask).await().id
        }

    override suspend fun save(pet: Pet): String =
        trace(SAVE_TASK_TRACE) {
            val updatedTask = pet.copy(userId = auth.currentUserId)
            firestore.collection(PET_COLLECTION).add(updatedTask).await().id
        }

    override suspend fun update(task: Task): Unit =
        trace(UPDATE_TASK_TRACE) {
            firestore.collection(TASK_COLLECTION).document(task.id).set(task).await()
        }

    override suspend fun update(pet: Pet): Unit =
        trace(UPDATE_TASK_TRACE) {
            firestore.collection(PET_COLLECTION).document(pet.id).set(pet).await()
        }

    override suspend fun deleteTask(taskId: String) {
        firestore.collection(TASK_COLLECTION).document(taskId).delete().await()
    }

    override suspend fun deletePet(petId: String) {
        firestore.collection(PET_COLLECTION).document(petId).delete().await()
    }

    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val TASK_COLLECTION = "tasks"
        private const val PET_COLLECTION = "pets"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}