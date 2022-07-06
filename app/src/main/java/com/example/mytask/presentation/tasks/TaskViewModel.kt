package com.example.mytask.presentation.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.use_cases.DeleteTask
import com.example.mytask.data.domain.use_cases.TaskUseCases
import com.example.mytask.data.domain.util.OrderType
import com.example.mytask.data.domain.util.TaskOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf<TasksState>(TasksState())
    val state: State<TasksState> = _state

    private var recentlyDeletedTask: Task? = null

    private var getTaskJob: Job? = null

    init {
        getTask(TaskOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: TasksEvent) {
        when(event) {
            is TasksEvent.Order -> {
                if (state.value.taskOrder::class == event.taskOrder::class &&
                    state.value.taskOrder.orderType == event.taskOrder.orderType) {
                    return
                }
                getTask(event.taskOrder)
            }
            is TasksEvent.Delete -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                    recentlyDeletedTask = event.task
                }
            }
            is TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }
            is TasksEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getTask(taskOrder: TaskOrder) {
        getTaskJob?.cancel()
        getTaskJob = taskUseCases.getTasks(taskOrder)
            .onEach { tasks ->
                _state.value = state.value.copy(tasks = tasks, taskOrder = taskOrder)

            }
            .launchIn(viewModelScope)
    }
}