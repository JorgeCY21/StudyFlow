package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.model.Course
import com.example.studyflow.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(
    private val courseRepository: CourseRepository,
) {
    operator fun invoke(): Flow<List<Course>> = courseRepository.observeCourses()
}
