package com.example.studyflow.domain.model

data class ProgressSummary(
    val totalHabits: Int,
    val totalTasks: Int,
    val completedTasks: Int,
    val completedHabits: Int,
    val pendingHabits: Int,
    val weeklyProgressPercent: Int,
    val pendingTasks: Int,
)
