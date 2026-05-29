package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.repository.TaskRepository

class AddTaskUseCase(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(title: String, courseName: String, deadline: String) {
        taskRepository.addTask(title, courseName, deadline)
    }
}
