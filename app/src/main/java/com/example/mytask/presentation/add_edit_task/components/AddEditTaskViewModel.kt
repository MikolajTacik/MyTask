package com.example.mytask.presentation.add_edit_task.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.data.domain.model.InvalidNoteException
import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.use_cases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _taskTitle = mutableStateOf(TaskTextFieldState(
        hint = "Enter title..."
    ))
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskContent = mutableStateOf(TaskTextFieldState(
        hint = "Enter content..."
    ))
    val taskContent: State<TaskTextFieldState> = _taskContent

    private val _taskColor = mutableStateOf(Task.taskColors.random().toArgb())
    val taskColor: State<Int> = _taskColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTask(taskId)?.also { task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
                        _taskContent.value = taskContent.value.copy(
                            text = task.content,
                            isHintVisible = false
                        )
                        _taskColor.value = task.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when(event) {
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskTitle.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.EnteredContent -> {
                _taskContent.value = _taskContent.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeContentFocus -> {
                _taskContent.value = _taskContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && _taskContent.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.ChangeColor -> {
                _taskColor.value = event.color
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTask(
                            Task(
                                title = taskTitle.value.text,
                                content = taskContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = taskColor.value,
                                id = currentTaskId

                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar (val message: String): UiEvent()
        object SaveTask: UiEvent()
    }
}