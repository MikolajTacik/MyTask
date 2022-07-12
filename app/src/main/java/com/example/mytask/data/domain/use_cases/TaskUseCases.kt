package com.example.mytask.data.domain.use_cases

data class TaskUseCases(
    val getTasks: GetTasks,
    val deleteTask: DeleteTask,
    val addTask: AddTask,
    val getTask: GetTask
) {
}