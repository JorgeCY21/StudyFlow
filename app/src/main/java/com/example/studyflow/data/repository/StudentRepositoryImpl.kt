package com.example.studyflow.data.repository

import com.example.studyflow.data.datasource.LocalStudentDataSource
import com.example.studyflow.domain.model.StudentProfile
import com.example.studyflow.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl(
    private val localStudentDataSource: LocalStudentDataSource,
) : StudentRepository {
    override fun observeStudentProfile(): Flow<StudentProfile> = localStudentDataSource.profile
}
