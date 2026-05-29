package com.example.studyflow.presentation.habits

import com.example.studyflow.domain.model.Habit

data class HabitUiState(
    val habits: List<Habit> = emptyList(),
)
