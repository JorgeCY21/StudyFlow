package com.example.studyflow.data.datasource

import com.example.studyflow.domain.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocalHabitDataSource {

    private val initialHabits = listOf(
        Habit(1, "Estudiar Kotlin", "Practicar funciones, clases y colecciones", "Programación", false),
        Habit(2, "Leer documentación", "Revisar guías oficiales antes de implementar", "Investigación", true),
        Habit(3, "Repasar inglés", "Vocabulario técnico para presentaciones", "Idiomas", false),
        Habit(4, "Practicar algoritmos", "Resolver ejercicios de complejidad básica", "Lógica", false),
    )

    private val _habits = MutableStateFlow(initialHabits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    fun addHabit(name: String, description: String, category: String) {
        val nextId = (_habits.value.maxOfOrNull { it.id } ?: 0) + 1
        _habits.update { currentHabits ->
            currentHabits + Habit(
                id = nextId,
                name = name,
                description = description,
                category = category,
                completed = false,
            )
        }
    }

    fun setHabitCompleted(habitId: Int, completed: Boolean) {
        _habits.update { currentHabits ->
            currentHabits.map { habit ->
                if (habit.id == habitId) habit.copy(completed = completed) else habit
            }
        }
    }
}
