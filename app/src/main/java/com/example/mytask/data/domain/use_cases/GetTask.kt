package com.example.mytask.data.domain.use_cases

import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.repository.TaskRepository
import java.net.IDN

class GetTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}