package com.example.petcareapp.data.tasks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf

data class TaskState(
    val tasks: List<Task> = emptyList(),
//    val showDialog: Boolean = false,
//    val taskName: String = "",
//    val petName: String = "",
//    val assignee: String = "",
//    val completed: Boolean = false,
//    val dueDate: Long = 0,
//    val dateAdded: Long = 0,

    val showDialog: MutableState<Boolean> = mutableStateOf(false),
    val isStoredByDateAdded: MutableState<Boolean> = mutableStateOf(true),
    val taskName: MutableState<String> = mutableStateOf(""),
    val petName: MutableState<String> = mutableStateOf(""),
    val assignee: MutableState<String> = mutableStateOf(""),
    val completed: MutableState<Boolean> = mutableStateOf(false),
    val dueDate: MutableState<Long> = mutableLongStateOf(0),
    val dateAdded: MutableState<Long> = mutableLongStateOf(0),
)
