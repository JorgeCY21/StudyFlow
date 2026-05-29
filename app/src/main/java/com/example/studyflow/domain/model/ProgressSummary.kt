package com.example.studyflow.domain.model

data class ProgressSummary(
    val totalTasks: Int,
    val completedTasks: Int,
    val completedHabits: Int,
    val weeklyProgressPercent: Int,
    val pendingTasks: Int,
)
