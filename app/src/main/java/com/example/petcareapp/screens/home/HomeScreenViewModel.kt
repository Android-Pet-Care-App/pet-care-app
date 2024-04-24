package com.example.petcareapp.screens.home

import com.example.petcareapp.model.Task
import com.example.petcareapp.model.service.LogService
import com.example.petcareapp.model.service.StorageService
import com.example.petcareapp.screens.PetCareAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : PetCareAppViewModel(logService) {
    val tasks = storageService.tasks

    fun onTaskComplete(task: Task) {
        launchCatching { storageService.update(task.copy(completed = task.completed)) }
    }


    // TODO: Implement Create Task Screen
//    fun onAddClick(openScreen: (String) -> Unit) = openScreen(CREATE_TASK_SCREEN)

    private fun onDeleteTaskClick(task: Task) {
        launchCatching { storageService.deleteTask(task.id) }
    }
}
