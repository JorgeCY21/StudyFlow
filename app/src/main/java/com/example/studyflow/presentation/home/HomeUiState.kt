package com.example.studyflow.presentation.home

import com.example.studyflow.domain.model.ProgressSummary
import com.example.studyflow.domain.model.StudentProfile

data class HomeUiState(
    val studentProfile: StudentProfile = StudentProfile(
        name = "",
        program = "",
        semester = "",
        focusGoal = "",
    ),
    val progressSummary: ProgressSummary = ProgressSummary(
        totalTasks = 0,
        completedTasks = 0,
        completedHabits = 0,
        weeklyProgressPercent = 0,
        pendingTasks = 0,
    ),
)
