package com.example.studyflow.presentation.progress

import com.example.studyflow.domain.model.ProgressSummary

data class ProgressUiState(
    val summary: ProgressSummary = ProgressSummary(
        totalTasks = 0,
        completedTasks = 0,
        completedHabits = 0,
        weeklyProgressPercent = 0,
        pendingTasks = 0,
    ),
)
