package com.example.studyflow.data.repository

import com.example.studyflow.data.datasource.LocalTaskDataSource
import com.example.studyflow.domain.model.Task
import com.example.studyflow.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val localTaskDataSource: LocalTaskDataSource,
) : TaskRepository {
    override fun observeTasks(): Flow<List<Task>> = localTaskDataSource.tasks

    override suspend fun addTask(title: String, courseName: String, deadline: String) {
        localTaskDataSource.addTask(title, courseName, deadline)
    }

    override suspend fun setTaskCompleted(taskId: Int, completed: Boolean) {
        localTaskDataSource.setTaskCompleted(taskId, completed)
    }
}
