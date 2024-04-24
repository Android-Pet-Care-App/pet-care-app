package com.example.petcareapp.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Task(
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val taskName: String = "",
    val petName: String = "",
    val assignee: String = "",
    val completed: Boolean = false,
    val dueDate: String = "",
    val dueTime: String = "",
    val userId: String = ""
)
