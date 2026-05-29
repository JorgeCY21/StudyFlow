package com.example.studyflow.presentation.tasks

import com.example.studyflow.domain.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
)
