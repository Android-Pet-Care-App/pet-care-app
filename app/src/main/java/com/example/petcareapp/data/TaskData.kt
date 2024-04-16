package com.example.petcareapp.data

data class TaskData(
    val taskName: String,
    val petName: String,
    val assignee: String,
    val dueDate: String?,
    val dueTime: String
) {
    companion object {
        val sampleTasks = listOf(
            TaskData(
                taskName = "Walk Johnny",
                petName = "Pet 1",
                assignee = "John Doe",
                dueDate = "May 15, 2024",
                dueTime = "10:00 AM"
            ),
            TaskData(
                taskName = "Feed Fido",
                petName = "Pet 2",
                assignee = "Jane Smith",
                dueDate = "May 16, 2024",
                dueTime = "12:00 PM"
            ),
            TaskData(
                taskName = "Clean litter box",
                petName = "Pet 3",
                assignee = "Alex Johnson",
                dueDate = "May 17, 2024",
                dueTime = "3:00 PM"
            )
        )
    }
}
