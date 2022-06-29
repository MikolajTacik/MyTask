package com.example.mytask.data.domain.repository

import com.example.mytask.data.domain.model.Task
import kotlinx.coroutines.flow.Flow


interface TaskRepository {

    fun getTask(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}
