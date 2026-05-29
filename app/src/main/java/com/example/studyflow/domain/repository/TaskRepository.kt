package com.example.studyflow.domain.repository

import com.example.studyflow.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun observeTasks(): Flow<List<Task>>

    suspend fun addTask(title: String, courseName: String, deadline: String)

    suspend fun setTaskCompleted(taskId: Int, completed: Boolean)
}
