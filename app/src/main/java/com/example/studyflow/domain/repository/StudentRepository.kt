package com.example.studyflow.domain.repository

import com.example.studyflow.domain.model.StudentProfile
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun observeStudentProfile(): Flow<StudentProfile>
}
