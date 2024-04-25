package com.example.petcareapp.screens.home

import androidx.compose.runtime.mutableStateOf
import com.example.petcareapp.CREATE_TASK_SCREEN
import com.example.petcareapp.HOME_SCREEN
import com.example.petcareapp.model.Task
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.model.service.StorageService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : PetCareAppViewModel(logService) {
    val tasks = storageService.tasks
    val uiState = mutableStateOf(HomeScreenUiState())

    fun onTaskComplete(task: Task) {
        launchCatching { storageService.update(task.copy(completed = true)) }
    }

    fun onTaskSelect(task: Task) {
        uiState.value.selectedTask = task
    }

    fun onAddTask(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            openAndPopUp(CREATE_TASK_SCREEN, HOME_SCREEN)
        }
    }

    fun onTaskDelete(task: Task) {
        launchCatching { storageService.deleteTask(task.id) }
    }
}
