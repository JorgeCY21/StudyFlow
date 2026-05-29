package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.model.Habit
import com.example.studyflow.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow

class GetHabitsUseCase(
    private val habitRepository: HabitRepository,
) {
    operator fun invoke(): Flow<List<Habit>> = habitRepository.observeHabits()
}
