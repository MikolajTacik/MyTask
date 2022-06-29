package com.example.mytask.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytask.data.domain.model.Task

@Database(entities = [Task::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
}