package com.example.studyflow.presentation.courses

import com.example.studyflow.domain.model.Course

data class CourseUiState(
    val courses: List<Course> = emptyList(),
)
