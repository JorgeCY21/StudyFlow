package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.repository.HabitRepository

class SetHabitCompletedUseCase(
    private val habitRepository: HabitRepository,
) {
    suspend operator fun invoke(habitId: Int, completed: Boolean) {
        habitRepository.setHabitCompleted(habitId, completed)
    }
}
