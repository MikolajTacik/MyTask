package com.example.mytask.di

import android.app.Application
import androidx.room.Room
import com.example.mytask.data.data_source.TaskDao
import com.example.mytask.data.data_source.TaskDatabase
import com.example.mytask.data.domain.repository.TaskRepository
import com.example.mytask.data.domain.use_cases.AddTask
import com.example.mytask.data.domain.use_cases.DeleteTask
import com.example.mytask.data.domain.use_cases.GetTasks
import com.example.mytask.data.domain.use_cases.TaskUseCases
import com.example.mytask.data.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
        TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            deleteTask = DeleteTask(repository),
            addTask = AddTask(repository)
        )
    }
}