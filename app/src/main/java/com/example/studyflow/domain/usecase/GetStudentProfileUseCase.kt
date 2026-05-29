package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.model.StudentProfile
import com.example.studyflow.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow

class GetStudentProfileUseCase(
    private val studentRepository: StudentRepository,
) {
    operator fun invoke(): Flow<StudentProfile> = studentRepository.observeStudentProfile()
}
