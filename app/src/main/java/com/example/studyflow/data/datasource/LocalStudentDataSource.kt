package com.example.studyflow.data.datasource

import com.example.studyflow.domain.model.StudentProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocalStudentDataSource {

    private val _profile = MutableStateFlow(
        StudentProfile(
            name = "StudyFlow Scholar",
            program = "Gestión académica",
            semester = "Ciclo activo",
            focusGoal = "Mantener hábitos y tareas al día",
        )
    )

    val profile: StateFlow<StudentProfile> = _profile.asStateFlow()
}
