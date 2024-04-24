package com.example.petcareapp.screens.create_task


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.petcareapp.TASK_ID
import com.example.petcareapp.common.ext.idFromParameter
import com.example.petcareapp.model.Task
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.model.service.StorageService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateTaskScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val storageService: StorageService,
): PetCareAppViewModel(logService){
    val task = mutableStateOf(Task())

    init {
        val taskId = savedStateHandle.get<String>(TASK_ID)
        Log.d("MyTag", taskId ?: "No task Id")
        if (taskId != null) {
            launchCatching {
                task.value = storageService.getTask(taskId.idFromParameter()) ?: Task()
            }
        }
    }


    fun onTaskNameChange(newValue: String) {
        task.value = task.value.copy(taskName = newValue)
    }

    fun onPetNameChange(newValue: String) {
        task.value = task.value.copy(petName = newValue)
    }

    fun onAssigneeChange(newValue: String) {
        task.value = task.value.copy(assignee = newValue)
    }

    fun onDueDateChange(newValue: Long) {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC))
        calendar.timeInMillis = newValue
        val newDueDate = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(calendar.time)
        task.value = task.value.copy(dueDate = newDueDate)
    }

    fun onTimeChange(hour: Int, minute: Int) {
        val newDueTime = "${hour.toClockPattern()}:${minute.toClockPattern()}"
        task.value = task.value.copy(dueTime = newDueTime)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val createdTask = task.value
            if (createdTask.id.isBlank()) {
                storageService.save(createdTask)
            } else {
                storageService.update(createdTask)
            }
            popUpScreen()
        }
    }

    private fun Int.toClockPattern(): String {
        return if (this < 10) "0$this" else "$this"
    }

    companion object {
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
    }
}