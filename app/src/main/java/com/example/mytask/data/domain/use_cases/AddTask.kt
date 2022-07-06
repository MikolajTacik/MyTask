package com.example.mytask.data.domain.use_cases

import com.example.mytask.data.domain.model.InvalidNoteException
import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(task: Task) {
        if(task.title.isBlank()) {
            throw InvalidNoteException("The title of the task can't be empty.")
        }
        if (task.content.isBlank()) {
            throw InvalidNoteException("The content of the task can't be empty.")
        }
        repository.insertTask(task)
    }
}