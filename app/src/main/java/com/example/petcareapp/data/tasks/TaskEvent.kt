package com.example.petcareapp.data.tasks

sealed interface TaskEvent {
    object SortTasks: TaskEvent
    data class SaveTask(val task: Task): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent
    data class CompleteTask(val taskId: Int): TaskEvent
    object ShowTaskDialog: TaskEvent
    object HideTaskDialog: TaskEvent
}