package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.repository.TaskRepository

class SetTaskCompletedUseCase(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(taskId: Int, completed: Boolean) {
        taskRepository.setTaskCompleted(taskId, completed)
    }
}
