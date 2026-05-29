package com.example.studyflow.domain.usecase

import com.example.studyflow.domain.repository.CourseRepository

class AddCourseUseCase(
    private val courseRepository: CourseRepository,
) {
    suspend operator fun invoke(name: String, teacher: String, taskCount: Int) {
        courseRepository.addCourse(name, teacher, taskCount)
    }
}
