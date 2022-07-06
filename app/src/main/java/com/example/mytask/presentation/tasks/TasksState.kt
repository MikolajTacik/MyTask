package com.example.mytask.presentation.tasks

import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.util.OrderType
import com.example.mytask.data.domain.util.TaskOrder

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
