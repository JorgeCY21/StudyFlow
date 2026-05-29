package com.example.studyflow.data.datasource

import com.example.studyflow.domain.model.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocalCourseDataSource {

    private val colorPalette = listOf(
        "#2563EB",
        "#7C3AED",
        "#0F766E",
        "#DB2777",
        "#EA580C",
        "#16A34A",
    )

    private val initialCourses = listOf(
        Course(1, "Desarrollo Avanzado en Nuevas Plataformas", "Ing. Carla Gómez", 1, "#2563EB"),
        Course(2, "Ingeniería de Software", "MSc. Daniel Pérez", 2, "#7C3AED"),
        Course(3, "Base de Datos", "MSc. Lucía Torres", 1, "#0F766E"),
    )

    private val _courses = MutableStateFlow(initialCourses)
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    fun addCourse(name: String, teacher: String, taskCount: Int) {
        val nextId = (_courses.value.maxOfOrNull { it.id } ?: 0) + 1
        val color = colorPalette[(nextId - 1) % colorPalette.size]
        _courses.update { currentCourses ->
            currentCourses + Course(
                id = nextId,
                name = name,
                teacher = teacher,
                taskCount = taskCount,
                colorHex = color,
            )
        }
    }
}
