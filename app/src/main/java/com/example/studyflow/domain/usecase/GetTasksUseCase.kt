package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.model.Task
import com.example.studyflow.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(): Flow<List<Task>> = taskRepository.observeTasks()
}
