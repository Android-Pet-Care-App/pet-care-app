package com.example.petcareapp.data.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val taskName: String,
    val petName: String,
    val assignee: String,
    val completed: Boolean,
    val dueDate: Long,
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
