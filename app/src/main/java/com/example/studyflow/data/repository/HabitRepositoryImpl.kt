package com.example.studyflow.data.repository

import com.example.studyflow.data.datasource.LocalHabitDataSource
import com.example.studyflow.domain.model.Habit
import com.example.studyflow.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow

class HabitRepositoryImpl(
    private val localHabitDataSource: LocalHabitDataSource,
) : HabitRepository {
    override fun observeHabits(): Flow<List<Habit>> = localHabitDataSource.habits

    override suspend fun addHabit(name: String, description: String, category: String) {
        localHabitDataSource.addHabit(name, description, category)
    }

    override suspend fun setHabitCompleted(habitId: Int, completed: Boolean) {
        localHabitDataSource.setHabitCompleted(habitId, completed)
    }
}
