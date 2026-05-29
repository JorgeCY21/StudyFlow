package com.example.studyflow.domain.repository

import com.example.studyflow.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    fun observeHabits(): Flow<List<Habit>>

    suspend fun addHabit(name: String, description: String, category: String)

    suspend fun setHabitCompleted(habitId: Int, completed: Boolean)
}
