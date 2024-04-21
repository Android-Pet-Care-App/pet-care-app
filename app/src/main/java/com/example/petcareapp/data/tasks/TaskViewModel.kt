package com.example.petcareapp.data.tasks

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val dao: TaskDao
): ViewModel() {
    private val isStoredByDateAdded: MutableStateFlow<Boolean> = MutableStateFlow(true)

    private val _tasks = isStoredByDateAdded.flatMapLatest { sort ->
        if (sort){
            dao.getTasksByDateAdded()
        }else{
            dao.getTasksByDueDate()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(TaskState())
    val state = combine(_state, isStoredByDateAdded,_tasks) {state,isStoredByDateAdded,tasks ->
            state.copy(
                isStoredByDateAdded = mutableStateOf(isStoredByDateAdded),
                tasks=tasks
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskState())

    fun onEvent(event: TaskEvent){
        when(event){
            TaskEvent.SortTasks -> { isStoredByDateAdded.value = !isStoredByDateAdded.value }
            TaskEvent.HideTaskDialog -> { _state.update { it.copy( showDialog = mutableStateOf(false))}}
            TaskEvent.ShowTaskDialog -> { _state.update { it.copy( showDialog = mutableStateOf( true ))}}
            is TaskEvent.DeleteTask -> {
                // for suspend funcs to make em syncronous
                viewModelScope.launch {
                    dao.delContact(event.task)
                }
            }
            is TaskEvent.CompleteTask -> {}
            is TaskEvent.SaveTask -> {
                val taskName = state.value.taskName.value
                val assignee = state.value.assignee.value
                val petName = state.value.petName.value
                val completed = state.value.completed.value
                val dateAdded = state.value.dateAdded.value
                val dueDate = state.value.dueDate.value
                if (taskName.isBlank() || assignee.isBlank() || petName.isBlank()){ return }

                val task = Task( taskName = taskName, assignee = assignee, petName = petName, completed = completed, dateAdded = dateAdded, dueDate = dueDate )
                viewModelScope.launch {
                    dao.addTask(task)
                }
                _state.update { it.copy(
                    showDialog = mutableStateOf(false),
                    taskName = mutableStateOf(""),
                    petName = mutableStateOf(""),
                    assignee = mutableStateOf(""),
                    completed = mutableStateOf(false),
                    dueDate = mutableLongStateOf(0),
                    dateAdded = mutableLongStateOf(0),
                ) }
            }
        }
    }
}