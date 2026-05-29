package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.repository.HabitRepository

class AddHabitUseCase(
    private val habitRepository: HabitRepository,
) {
    suspend operator fun invoke(name: String, description: String, category: String) {
        habitRepository.addHabit(name, description, category)
    }
}
