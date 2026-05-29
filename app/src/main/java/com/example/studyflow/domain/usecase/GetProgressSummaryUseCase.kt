package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.model.ProgressSummary
import com.example.studyflow.domain.repository.HabitRepository
import com.example.studyflow.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GetProgressSummaryUseCase(
    private val habitRepository: HabitRepository,
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(): Flow<ProgressSummary> {
        return combine(
            habitRepository.observeHabits(),
            taskRepository.observeTasks(),
        ) { habits, tasks ->
            val completedHabits = habits.count { it.completed }
            val completedTasks = tasks.count { it.completed }
            val totalWorkItems = habits.size + tasks.size
            val completedWorkItems = completedHabits + completedTasks
            val progressPercent = if (totalWorkItems == 0) 0 else (completedWorkItems * 100 / totalWorkItems)

            ProgressSummary(
                totalTasks = tasks.size,
                completedTasks = completedTasks,
                completedHabits = completedHabits,
                weeklyProgressPercent = progressPercent,
                pendingTasks = tasks.count { !it.completed },
            )
        }
    }
}
