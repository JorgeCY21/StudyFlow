package com.example.studyflow.presentation.tasks

import com.example.studyflow.domain.model.Course
import com.example.studyflow.domain.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val courses: List<Course> = emptyList(),
)
