package com.example.mytask.data.domain.use_cases

import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.repository.TaskRepository

class DeleteTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}