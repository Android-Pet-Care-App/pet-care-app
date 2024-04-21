package com.example.petcareapp.data.tasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: Task) // suspend waits to finish opperation and then moves on (sync)

    @Delete
    suspend fun delContact(task: Task)

    @Query("SELECT * from Task ORDER BY dateAdded")
    fun getTasksByDateAdded(): Flow<List<Task>>

    @Query("SELECT * from Task ORDER BY dueDate")
    fun getTasksByDueDate(): Flow<List<Task>>
}