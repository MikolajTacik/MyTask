package com.example.mytask.presentation.tasks

import android.provider.ContactsContract
import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.util.TaskOrder

sealed class TasksEvent {
    data class  Order(val taskOrder: TaskOrder): TasksEvent()
    data class Delete(val task: Task): TasksEvent()
    object RestoreTask: TasksEvent()
    object ToggleOrderSection: TasksEvent()

}
