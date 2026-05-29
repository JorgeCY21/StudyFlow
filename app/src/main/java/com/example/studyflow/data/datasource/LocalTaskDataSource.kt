package com.example.studyflow.data.datasource

import com.example.studyflow.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocalTaskDataSource {

    private val initialTasks = listOf(
        Task(1, "Laboratorio SOLID en Compose", "Desarrollo Avanzado en Nuevas Plataformas", "Viernes 31/05", false),
        Task(2, "Informe de arquitectura", "Ingeniería de Software", "Martes 04/06", true),
        Task(3, "Exposición de proyecto", "Base de Datos", "Jueves 06/06", false),
    )

    private val _tasks = MutableStateFlow(initialTasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(title: String, courseName: String, deadline: String) {
        val nextId = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1
        _tasks.update { currentTasks ->
            currentTasks + Task(
                id = nextId,
                title = title,
                courseName = courseName,
                deadline = deadline,
                completed = false,
            )
        }
    }

    fun setTaskCompleted(taskId: Int, completed: Boolean) {
        _tasks.update { currentTasks ->
            currentTasks.map { task ->
                if (task.id == taskId) task.copy(completed = completed) else task
            }
        }
    }
}
