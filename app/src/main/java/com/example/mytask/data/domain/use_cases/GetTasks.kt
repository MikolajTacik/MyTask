package com.example.mytask.data.domain.use_cases

import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.repository.TaskRepository
import com.example.mytask.data.domain.util.OrderType
import com.example.mytask.data.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
) {

    operator fun invoke(taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending)): Flow<List<Task>> {
        return repository.getTask().map { tasks ->
            when (taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when(taskOrder) {
                        is TaskOrder.Title -> tasks.sortedBy { it.title.lowercase() }
                        is TaskOrder.Date -> tasks.sortedBy { it.timestamp }
                        is TaskOrder.Color -> tasks.sortedBy { it.color}
                    }
                }
                is OrderType.Descending -> {
                    when(taskOrder) {
                        is TaskOrder.Title -> tasks.sortedByDescending { it.title.lowercase() }
                        is TaskOrder.Date -> tasks.sortedByDescending { it.timestamp }
                        is TaskOrder.Color -> tasks.sortedByDescending { it.color}
                    }
                }
            }
        }
    }
}