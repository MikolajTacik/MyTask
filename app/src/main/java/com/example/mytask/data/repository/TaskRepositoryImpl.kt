package com.example.mytask.data.repository

import com.example.mytask.data.data_source.TaskDao
import com.example.mytask.data.domain.model.Task
import com.example.mytask.data.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {
    override fun getTask(): Flow<List<Task>> {
        return dao.getTask()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        return dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return dao.deleteTask(task)
    }
}